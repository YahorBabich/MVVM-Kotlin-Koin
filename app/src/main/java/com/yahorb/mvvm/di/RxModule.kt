package com.yahorb.mvvm.di

import com.yahorb.mvvm.util.rx.ApplicationSchedulerProvider
import com.yahorb.mvvm.util.rx.SchedulerProvider
import org.koin.dsl.module

val rxModule = module {
    single<SchedulerProvider> { ApplicationSchedulerProvider() }
}