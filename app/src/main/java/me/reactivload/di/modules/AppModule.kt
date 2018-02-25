package me.reactivload.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import me.reactivload.data.network.ApiConfig
import me.reactivload.data.network.ApiHelper
import me.reactivload.data.network.ApiHelperImpl
import me.reactivload.data.network.MyCurrencyApi
import me.reactivload.di.AppScope
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Shashank on 28/10/2017.
 */
@Module
class AppModule {

    @Provides
    @AppScope
    internal fun provideContext(application: Application): Context = application

    @Provides
    @AppScope
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @AppScope
    fun provideMyCurrencyApi(retrofit: Retrofit): MyCurrencyApi {
        return retrofit.create<MyCurrencyApi>(MyCurrencyApi::class.java)
    }

    @Provides
    @AppScope
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
    }

    @Provides
    @AppScope
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @AppScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .followSslRedirects(true)
                .addInterceptor(loggingInterceptor)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .connectionPool(ConnectionPool(30, 120, TimeUnit.SECONDS))
                .build()
    }

    @Provides
    @AppScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}
