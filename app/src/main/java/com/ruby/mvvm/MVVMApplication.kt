package com.ruby.mvvm

import android.app.Application
import com.ruby.mvvm.di.localAndroidDatasourceModule
import com.ruby.mvvm.di.weatherApp
import org.koin.android.ext.android.startKoin

class MVVMApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, weatherApp + localAndroidDatasourceModule)
    }
}
