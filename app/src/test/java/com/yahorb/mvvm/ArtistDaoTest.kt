package com.yahorb.mvvm

import android.content.Context
import com.yahorb.mvvm.database.ArtistDao
import com.yahorb.mvvm.database.ArtistDatabase
import com.yahorb.mvvm.di.databaseModules
import com.yahorb.mvvm.di.networkModule
import com.yahorb.mvvm.di.rxModule
import com.yahorb.mvvm.di.vmModule
import com.yahorb.mvvm.model.data.Artist
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class ArtistDaoTest : KoinTest, KoinComponent {
    /*@MockK
     var artistDao: ArtistDao*/


/*    @Mock
    private lateinit var context: Context*/
    /* @Rule
     @JvmField
     var instantTaskExecutorRule = InstantTaskExecutorRule()

     val weatherEntity1 = createWeatherEntity()
     val weatherEntity2 = createWeatherEntity()
     val weatherEntity3 = createWeatherEntity()*/

   /* val context by inject<Context>()
    val artistDatabase by inject<ArtistDatabase>()
    val artistDao by inject<ArtistDao>()*/

    @Before
    fun before() {
        startKoin {
            modules(listOf(vmModule, rxModule, networkModule, databaseModules))
        }
    }

    @After
    fun after() {
        //artistDatabase.close()
        stopKoin()
    }

/*    @get:Rule
    val koinTestRule = KoinTestRule.create {
        // Your KoinApplication instance here
        modules(myModule)
    }*/

    @Test
    fun firstTest() {
        //val repositoryMock = mock(Repository::class.java)

        val a1 = Artist(artistId = 1000, artistName = "name1")
        val a2 = Artist(artistId = 1001, artistName = "name2")
        val a3 = Artist(artistId = 1001, artistName = "name3")
        //artistDao.insertAll(*listOf(a1, a2, a3).toTypedArray())
        //artistDao.getAll()
    }
}