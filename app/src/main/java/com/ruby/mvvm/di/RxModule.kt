package com.ruby.mvvm.di

import com.ruby.mvvm.util.rx.ApplicationSchedulerProvider
import com.ruby.mvvm.util.rx.SchedulerProvider
import org.koin.dsl.module

val rxModule = module {
    // provided components
    single { ApplicationSchedulerProvider() as SchedulerProvider }
}