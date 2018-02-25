package me.reactivload

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.microblink.activity.SegmentScanActivity
import com.microblink.ocr.ScanConfiguration
import com.microblink.recognizers.blinkocr.parser.generic.AmountParserSettings
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_scan.setOnClickListener {
            val intent = Intent(this, SegmentScanActivity::class.java)
            intent.putExtra(SegmentScanActivity.EXTRAS_LICENSE_KEY, "OSTWCCMB-BQVMDV43-PD2KGQCW-EXSXLKJV-C223CSS4-S4HSIQX7-VLWDGYSU-LFD7I5RS")
            val confArray = arrayOf<ScanConfiguration>(ScanConfiguration(R.string.amount_title, R.string.amount_msg,
                    "Amount",
                    AmountParserSettings()))
            intent.putExtra(SegmentScanActivity.EXTRAS_SCAN_CONFIGURATION, confArray)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == SegmentScanActivity.RESULT_OK && data != null) {
                // perform processing of the data here

                // for example, obtain parcelable recognition result
                val extras = data.extras
                val results = extras.getBundle(SegmentScanActivity.EXTRAS_SCAN_RESULTS)

                val amount = results.getString("Amount")
                edittext_usd.setText(amount)
            }
        }
    }
}
