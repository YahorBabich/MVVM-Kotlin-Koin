package com.ruby.mvvm

import android.app.Application
import com.ruby.mvvm.di.storageModule
import com.ruby.mvvm.di.rxModule
import com.ruby.mvvm.di.vmModule
import org.koin.android.ext.android.startKoin

class MVVMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(vmModule, rxModule, storageModule))
    }
}