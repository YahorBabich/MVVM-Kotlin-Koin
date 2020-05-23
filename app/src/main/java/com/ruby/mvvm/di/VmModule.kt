package com.ruby.mvvm.di

import com.ruby.mvvm.repository.WeatherRepositoryImpl
import com.ruby.mvvm.repository.local.WeatherRepository
import com.ruby.mvvm.view.detail.DetailViewModel
import com.ruby.mvvm.view.list.ListViewModel
import com.ruby.mvvm.view.search.SearchViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext


val vmModule = applicationContext {

    // ViewModel for Search View
    viewModel { SearchViewModel(get(), get()) }

    // ViewModel for Result View
    viewModel { ListViewModel(get(), get()) }

    // ViewModel for Detail View
    viewModel { params -> DetailViewModel(params["id"], get(), get()) }

    // Weather Data Repository
    bean { WeatherRepositoryImpl(get()) as WeatherRepository }
}
