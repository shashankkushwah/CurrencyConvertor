package me.reactivload.data.network

import io.reactivex.Observable
import me.reactivload.data.model.CurrencyRate
import javax.inject.Inject

/**
 * Created by Shashank on 22/10/2017.
 */
class ApiHelperImpl @Inject constructor(private val api: MyCurrencyApi) : ApiHelper {
    override fun getRates(): Observable<List<CurrencyRate>> {
        return api.getRates()
    }
}