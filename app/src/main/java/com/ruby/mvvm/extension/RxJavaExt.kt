package com.ruby.mvvm.extension

import com.ruby.mvvm.util.rx.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Use SchedulerProvider configuration for Single
 */
fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> =
    observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun Completable.with(schedulerProvider: SchedulerProvider): Completable =
    observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())