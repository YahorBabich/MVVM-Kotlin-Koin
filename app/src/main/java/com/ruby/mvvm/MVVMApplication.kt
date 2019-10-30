package com.ruby.mvvm

import android.app.Application
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.WeathericonsModule
import org.koin.android.ext.android.startKoin
import com.ruby.mvvm.di.localAndroidDatasourceModule
import com.ruby.mvvm.di.weatherApp

class MVVMApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, weatherApp + localAndroidDatasourceModule)
        Iconify.with(WeathericonsModule())
    }
}
