package me.reactivload.data.network

import io.reactivex.Observable
import me.reactivload.data.model.CurrencyRate

/**
 * Created by Shashank on 22/10/2017.
 */
interface ApiHelper {

    fun getRates(): Observable<List<CurrencyRate>>

}