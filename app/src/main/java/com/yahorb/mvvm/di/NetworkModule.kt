package com.yahorb.mvvm.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.yahorb.mvvm.BuildConfig
import com.yahorb.mvvm.network.AuthInterceptor
import com.yahorb.mvvm.network.BASE_URL
import com.yahorb.mvvm.repository.UsersApi
import com.yahorb.mvvm.repository.WeatherRepositoryImpl
import com.yahorb.mvvm.repository.local.WeatherRepository
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get(), get()) }
    factory { provideUserApi(get()) }
    factory { provideLoggingInterceptor() }
    single { provideRetrofit(get()) }

    //   single { createWebService<WeatherDatasource>(get(), getProperty(SERVER_URL)) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
}

fun provideOkHttpClient(
    authInterceptor: AuthInterceptor,
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val builder = OkHttpClient().newBuilder().addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
    if (BuildConfig.DEBUG) {
        builder.addNetworkInterceptor(StethoInterceptor())
    }
    return builder.build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BASIC
    return logger
}

fun provideUserApi(retrofit: Retrofit): UsersApi = retrofit.create(
    UsersApi::class.java
)

/*inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}*/
