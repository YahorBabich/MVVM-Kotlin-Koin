package com.yahorb.mvvm.di

import com.yahorb.mvvm.repository.ITunesAPI
import com.yahorb.mvvm.repository.local.LocalDataSource
import org.koin.dsl.module

val storageModule = module {
    //single<JsonReader> { AndroidJsonReader(androidApplication()) }
    /*single<ITunesAPI> { LocalDataSource(get()) }*/
}