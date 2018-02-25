package me.reactivload.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import me.reactivload.CurrencyApplication
import me.reactivload.di.modules.ActivityBuilder
import me.reactivload.di.modules.AppModule

/**
 * Created by Shashank on 28/10/2017.
 */
@AppScope
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: CurrencyApplication)
}