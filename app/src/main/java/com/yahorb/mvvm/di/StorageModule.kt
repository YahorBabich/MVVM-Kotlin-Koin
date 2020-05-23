package com.yahorb.mvvm.di

import com.yahorb.mvvm.repository.WeatherDatasource
import com.yahorb.mvvm.repository.local.AndroidJsonReader
import com.yahorb.mvvm.repository.local.JsonReader
import com.yahorb.mvvm.repository.local.LocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val storageModule = module {
    single<JsonReader> { AndroidJsonReader(androidApplication()) }
    single<WeatherDatasource> { LocalDataSource(get()) }
}