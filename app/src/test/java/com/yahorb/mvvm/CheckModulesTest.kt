package com.yahorb.mvvm

import com.yahorb.mvvm.di.databaseModules
import com.yahorb.mvvm.di.networkModule
import com.yahorb.mvvm.di.rxModule
import com.yahorb.mvvm.di.vmModule
import org.junit.Before
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules

@Category(CheckModuleTest::class)
class CheckModulesTest : AutoCloseKoinTest() {

    @Before
    fun before() {
        startKoin {
            modules(listOf(vmModule, rxModule, networkModule, databaseModules))
        }
    }

    @Test
    fun checkAllModules() = checkModules {
        modules(vmModule, rxModule, networkModule, databaseModules)
    }
}