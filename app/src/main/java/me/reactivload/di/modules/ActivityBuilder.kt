package me.reactivload.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.reactivload.ui.main.MainActivity
import me.reactivload.ui.main.MainActivityModule

/**
 * Created by shashank on 12/02/2018.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

}