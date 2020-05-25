package com.yahorb.mvvm.di

import androidx.room.Room
import com.yahorb.mvvm.database.AppDatabase
import com.yahorb.mvvm.util.Constants.DB_NAME
import org.koin.dsl.module

val databaseModules = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, DB_NAME).build() }
    single { get<AppDatabase>().artistDao() }
}