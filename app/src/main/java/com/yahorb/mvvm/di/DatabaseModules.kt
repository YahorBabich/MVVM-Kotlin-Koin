package com.yahorb.mvvm.di

import androidx.room.Room
import com.yahorb.mvvm.database.AppDatabase
import org.koin.dsl.module

val databaseModules = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "artists").build() }
    single { get<AppDatabase>().artistDao() }
}