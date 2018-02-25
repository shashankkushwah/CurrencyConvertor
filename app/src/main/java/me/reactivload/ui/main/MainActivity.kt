package me.reactivload.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.microblink.activity.SegmentScanActivity
import com.microblink.locale.LanguageUtils
import com.microblink.ocr.ScanConfiguration
import com.microblink.recognizers.blinkocr.parser.generic.AmountParserSettings
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import me.reactivload.R
import me.reactivload.data.model.CurrencyRate
import me.reactivload.data.network.ApiHelper
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 10
    private val EXTRA_AMOUNT = "Amount"

    @Inject
    lateinit var apiHelper: ApiHelper

    private var currencyRates: List<CurrencyRate> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        button_scan.setOnClickListener {
            val intent = Intent(this, SegmentScanActivity::class.java)
            intent.putExtra(SegmentScanActivity.EXTRAS_LICENSE_KEY, "OSTWCCMB-BQVMDV43-PD2KGQCW-EXSXLKJV-C223CSS4-S4HSIQX7-VLWDGYSU-LFD7I5RS")

            LanguageUtils.setLanguageAndCountry("en", "UK", this)
            val amountParserSettings = AmountParserSettings()
            amountParserSettings.setAllowNegativeAmounts(true)
            amountParserSettings.setAllowMissingDecimals(true)
            val confArray = arrayOf(ScanConfiguration(R.string.amount_title, R.string.amount_msg,
                    EXTRA_AMOUNT,
                    amountParserSettings))
            intent.putExtra(SegmentScanActivity.EXTRAS_SCAN_CONFIGURATION, confArray)
            startActivityForResult(intent, REQUEST_CODE)
        }

        apiHelper.getRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    currencyRates = it
                }, {
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG)
                            .show()
                })

        edittext_usd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val amount = s.toString()
                        .trim()
                if (amount.isNotEmpty()) {
                    convert(amount)
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == SegmentScanActivity.RESULT_OK && data != null) {
                val extras = data.extras
                val results = extras.getBundle(SegmentScanActivity.EXTRAS_SCAN_RESULTS)

                val amountStr = results.getString("Amount")
                val amount = NumberFormat.getInstance(Locale.ITALIAN)
                        .parse(amountStr)
                convert(amount.toString())
                edittext_usd.setText(amount.toString())
                edittext_usd.setSelection(edittext_usd.text.length)
            }
        }
    }

    private fun convert(amount: String) {
        var currency: CurrencyRate? = null
        currencyRates.forEach {
            if (it.currency_code == "GBP") {
                currency = it
            }
        }

        if (currency != null && amount.isNotEmpty()) {
            val amountBigDecimal = amount.toBigDecimal()
            val rateBigDecimal = currency!!.rate.toBigDecimal()

            val gbpBigDecimal = amountBigDecimal.multiply(rateBigDecimal)
            edittext_gbp.setText(gbpBigDecimal.toPlainString())
        } else {
            edittext_gbp.setText("conversion failed")
        }
    }
}
