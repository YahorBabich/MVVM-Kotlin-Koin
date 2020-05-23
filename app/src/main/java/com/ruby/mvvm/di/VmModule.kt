package com.ruby.mvvm.di

import com.ruby.mvvm.repository.WeatherRepositoryImpl
import com.ruby.mvvm.repository.local.WeatherRepository
import com.ruby.mvvm.view.detail.DetailViewModel
import com.ruby.mvvm.view.list.ListViewModel
import com.ruby.mvvm.view.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    // ViewModel for Search View
    viewModel { SearchViewModel(get(), get()) }

    // ViewModel for Result View
    viewModel { ListViewModel(get(), get()) }

    // ViewModel for Detail View
    viewModel { DetailViewModel(get(), get()) }

    // Weather Data Repository
    single { WeatherRepositoryImpl(get()) as WeatherRepository }
}