package com.ruby.mvvm

import android.app.Application
import com.ruby.mvvm.di.rxModule
import com.ruby.mvvm.di.storageModule
import com.ruby.mvvm.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MVVMApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MVVMApplication)
            androidFileProperties()
            modules(listOf(vmModule, storageModule, rxModule))
        }
    }
}