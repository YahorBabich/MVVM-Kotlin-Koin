package com.yahorb.mvvm.view.search

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import com.yahorb.mvvm.database.ArtistDatabase
import com.yahorb.mvvm.di.networkModule
import com.yahorb.mvvm.di.rxModule
import com.yahorb.mvvm.di.vmModule
import com.yahorb.mvvm.model.data.Artist
import com.yahorb.mvvm.model.data.Term
import com.yahorb.mvvm.util.Constants
import com.yahorb.mvvm.view.search.model.SearchModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class SearchViewModelTest : AutoCloseKoinTest() {

    private var context: Context = mock(Context::class.java)

    @Mock
    private lateinit var listObserver: Observer<SearchModel>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val viewModel: SearchViewModel by inject()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            modules(listOf(vmModule, rxModule, networkModule, module {
                single {
                    Room.databaseBuilder(
                        context,
                        ArtistDatabase::class.java,
                        Constants.DB_NAME
                    ).build()
                }
                single { get<ArtistDatabase>().artistDao() }
            }))
        }
    }

    @Test
    fun `test event when emptyList`() {
        viewModel.searchEvent.observeForever(listObserver)
        viewModel.onSearchSuccess(Term(2, emptyList()))
        Mockito.verify(listObserver).onChanged(SearchModel(true))
    }

    @Test
    fun `test event when result = 0`() {
        viewModel.searchEvent.observeForever(listObserver)
        viewModel.onSearchSuccess(Term(0, listOf(Artist(100, "name"))))
        Mockito.verify(listObserver).onChanged(SearchModel(true))
    }
}