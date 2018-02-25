package me.reactivload.ui.main

import android.icu.util.CurrencyAmount
import dagger.Module
import dagger.Provides

/**
 * Created by Shashank Kushwah on 12/02/2018.
 */
@Module
class MainActivityModule {

    @Provides
    fun provideMutableList(): MutableList<CurrencyAmount> = mutableListOf()
}