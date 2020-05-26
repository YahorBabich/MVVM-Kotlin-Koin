package com.yahorb.mvvm.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.yahorb.mvvm.BuildConfig
import com.yahorb.mvvm.repository.ITunesAPI
import com.yahorb.mvvm.repository.ITunesRepositoryImpl
import com.yahorb.mvvm.repository.ITunesRepository
import com.yahorb.mvvm.util.Constants.BASE_URL
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideOkHttpClient(get()) }
    factory { provideITunesApi(get()) }
    factory { provideLoggingInterceptor() }
    factory { provideRetrofit(get()) }

    single<ITunesRepository> { ITunesRepositoryImpl(get(), get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
}

fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
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

fun provideITunesApi(retrofit: Retrofit): ITunesAPI = retrofit.create(
    ITunesAPI::class.java
)
