package me.reactivload.di

import dagger.Component
import me.reactivload.di.modules.AppModule

/**
 * Created by Shashank on 28/10/2017.
 */

@ApplicationScope
@Component(modules = [(AppModule::class)])
interface AppComponent {

//    fun getApiHelper(): ApiHelper

}