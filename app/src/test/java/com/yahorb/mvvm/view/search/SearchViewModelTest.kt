package com.yahorb.mvvm.view.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.yahorb.mvvm.BaseTest
import com.yahorb.mvvm.model.data.Artist
import com.yahorb.mvvm.model.data.Term
import com.yahorb.mvvm.view.search.model.SearchModel
import org.junit.Rule
import org.junit.Test
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.verify

class SearchViewModelTest : BaseTest() {
    @Mock
    private lateinit var listObserver: Observer<SearchModel>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val viewModel: SearchViewModel by inject()

    @Test
    fun `test event when emptyList`() {
        viewModel.searchEvent.observeForever(listObserver)
        viewModel.onSearchSuccess(Term(2, emptyList()))
        verify(listObserver).onChanged(SearchModel(true))
    }

    @Test
    fun `test event when result = 0`() {
        viewModel.searchEvent.observeForever(listObserver)
        viewModel.onSearchSuccess(Term(0, listOf(Artist(100, "name"))))
        verify(listObserver).onChanged(SearchModel(true))
    }
}