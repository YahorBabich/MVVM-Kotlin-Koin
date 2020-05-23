package com.ruby.mvvm

import android.app.Application
import com.facebook.stetho.Stetho
import com.ruby.mvvm.di.*
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
            modules(listOf(vmModule, storageModule, rxModule, networkModule, databaseModules))
        }

        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                Stetho.newInitializerBuilder(this@MVVMApplication)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this@MVVMApplication))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this@MVVMApplication))
                    .build()
            )
        }
    }
}