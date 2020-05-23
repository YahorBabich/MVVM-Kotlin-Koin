package com.ruby.mvvm.di

import com.ruby.mvvm.repository.WeatherDatasource
import com.ruby.mvvm.repository.local.AndroidJsonReader
import com.ruby.mvvm.repository.local.JsonReader
import com.ruby.mvvm.repository.local.LocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val storageModule = applicationContext {
    bean { AndroidJsonReader(androidApplication()) as JsonReader }
    bean { LocalDataSource(get()) as WeatherDatasource }
}