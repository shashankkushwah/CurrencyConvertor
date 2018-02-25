package me.reactivload

import android.app.Activity
import android.app.Application
import me.reactivload.di.AppComponent
import me.reactivload.di.DaggerApplicationComponent
import me.reactivload.di.modules.AppModule

/**
 * Created by shashank on 25/02/2018.
 */
class CurrencyApplication : Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun getApplicationComponent(): AppComponent {
        return component
    }

    companion object {
        fun get(activity: Activity): CurrencyApplication {
            return activity.applicationContext as CurrencyApplication
        }
    }
}