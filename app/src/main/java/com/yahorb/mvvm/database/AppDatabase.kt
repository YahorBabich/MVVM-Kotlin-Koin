package com.yahorb.mvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yahorb.mvvm.model.data.Artist

@Database(entities = [Artist::class], version = 7)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
}