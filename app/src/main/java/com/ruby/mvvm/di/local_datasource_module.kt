package com.ruby.mvvm.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext
import com.ruby.mvvm.repository.WeatherDatasource
import com.ruby.mvvm.repository.local.AndroidJsonReader
import com.ruby.mvvm.repository.local.JsonReader
import com.ruby.mvvm.repository.local.LocalDataSource

val localAndroidDatasourceModule = applicationContext {
    bean { AndroidJsonReader(androidApplication()) as JsonReader }
    bean { LocalDataSource(get()) as WeatherDatasource }
}