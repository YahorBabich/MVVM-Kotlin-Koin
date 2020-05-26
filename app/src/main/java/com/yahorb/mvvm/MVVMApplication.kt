package com.yahorb.mvvm

import android.app.Application
import com.facebook.stetho.Stetho
import com.yahorb.mvvm.di.databaseModules
import com.yahorb.mvvm.di.networkModule
import com.yahorb.mvvm.di.rxModule
import com.yahorb.mvvm.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class MVVMApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) androidLogger() else EmptyLogger()
            androidContext(this@MVVMApplication)
            androidFileProperties()
            modules(listOf(vmModule, rxModule, networkModule, databaseModules))
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