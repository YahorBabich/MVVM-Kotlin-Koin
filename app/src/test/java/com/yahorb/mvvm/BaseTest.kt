package com.yahorb.mvvm

import android.content.Context
import androidx.room.Room
import com.yahorb.mvvm.database.ArtistDatabase
import com.yahorb.mvvm.di.networkModule
import com.yahorb.mvvm.di.rxModule
import com.yahorb.mvvm.di.vmModule
import com.yahorb.mvvm.util.Constants
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

open class BaseTest : AutoCloseKoinTest() {

    private var context: Context = mock(Context::class.java)

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
}