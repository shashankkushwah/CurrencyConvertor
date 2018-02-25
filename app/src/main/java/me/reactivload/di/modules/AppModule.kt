package me.reactivload.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import me.reactivload.di.ApplicationScope

/**
 * Created by Shashank on 28/10/2017.
 */
@Module
class AppModule(val context: Context) {


    @Provides
    @ApplicationScope
    fun context(): Context {
        return context
    }
//
//    @Provides
//    @ApplicationScope
//    fun apiHelper(retrofit: Retrofit): ApiHelper {
//        return ApiHelperImpl(retrofit.create<MyCurrencyApi>(MyCurrencyApi::class.java))
//    }
//
//    @Provides
//    @ApplicationScope
//    fun retrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//                .baseUrl(ApiConfig.BASE_ENDPOINT)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(okHttpClient)
//                .build()
//    }
//
//    @Provides
//    @ApplicationScope
//    fun gson(): Gson {
//        return GsonBuilder().create()
//    }
//
//    @Provides
//    @ApplicationScope
//    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
//        return OkHttpClient().newBuilder()
//                .readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .followSslRedirects(true)
//                .addInterceptor(loggingInterceptor)
//                .followRedirects(true)
//                .retryOnConnectionFailure(true)
//                .connectionPool(ConnectionPool(30, 120, TimeUnit.SECONDS))
//                .build()
//    }
//
//    @Provides
//    @ApplicationScope
//    fun loggingInterceptor(): HttpLoggingInterceptor {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        return loggingInterceptor
//    }
}
