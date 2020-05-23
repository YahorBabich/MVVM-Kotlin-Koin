package com.ruby.mvvm.di

import com.ruby.mvvm.util.rx.ApplicationSchedulerProvider
import com.ruby.mvvm.util.rx.SchedulerProvider
import org.koin.dsl.module.applicationContext

val rxModule = applicationContext {
    // provided components
    bean { ApplicationSchedulerProvider() as SchedulerProvider }
}