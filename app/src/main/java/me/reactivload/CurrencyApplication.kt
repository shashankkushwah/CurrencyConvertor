package me.reactivload

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.reactivload.di.AppComponent
import me.reactivload.di.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by shashank on 25/02/2018.
 */
class CurrencyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = activityDispatchingAndroidInjector

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
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