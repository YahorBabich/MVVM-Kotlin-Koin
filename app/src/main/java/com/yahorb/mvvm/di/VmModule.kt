package com.yahorb.mvvm.di

import com.yahorb.mvvm.view.detail.DetailViewModel
import com.yahorb.mvvm.view.list.ListViewModel
import com.yahorb.mvvm.view.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}