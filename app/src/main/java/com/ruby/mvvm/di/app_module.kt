package com.ruby.mvvm.di

import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import com.ruby.mvvm.repository.WeatherRepositoryImpl
import com.ruby.mvvm.repository.local.WeatherRepository
import com.ruby.mvvm.util.rx.ApplicationSchedulerProvider
import com.ruby.mvvm.util.rx.SchedulerProvider
import com.ruby.mvvm.view.detail.DetailViewModel
import com.ruby.mvvm.view.list.ListViewModel
import com.ruby.mvvm.view.search.SearchViewModel


val weatherModule = applicationContext {

    // ViewModel for Search View
    viewModel { SearchViewModel(get(), get()) }

    // ViewModel for Result View
    viewModel { ListViewModel(get(), get()) }

    // ViewModel for Detail View
    viewModel { params -> DetailViewModel(params["id"],get(), get()) }

    // Weather Data Repository
    bean { WeatherRepositoryImpl(get()) as WeatherRepository }
}

val rxModule = applicationContext {
    // provided components
    bean { ApplicationSchedulerProvider() as SchedulerProvider }
}

// Gather all app modules
val weatherApp = listOf(weatherModule, rxModule)
