package me.reactivload.data.network

import io.reactivex.Observable
import me.reactivload.data.model.CurrencyRate
import retrofit2.http.GET

/**
 * Created by Shashank on 22/10/2017.
 */
interface MyCurrencyApi {

    @GET("rates")
    fun getRates(): Observable<List<CurrencyRate>>

}