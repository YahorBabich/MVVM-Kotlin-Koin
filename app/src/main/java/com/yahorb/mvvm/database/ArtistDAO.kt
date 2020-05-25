package com.yahorb.mvvm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yahorb.mvvm.model.data.Artist
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ArtistDao {
    @get:Query("SELECT * FROM artist")
    val getAll: Flowable<List<Artist>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg artist: Artist): Completable

    @Query("SELECT * FROM artist WHERE artistId=:id ")
    fun getByID(id: Int): Flowable<Artist>

    @Query("DELETE FROM artist")
    fun deleteAll(): Completable
}