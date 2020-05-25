package com.yahorb.mvvm.extension

import com.yahorb.mvvm.util.rx.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Use SchedulerProvider configuration for Single
 */
fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> =
    observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun Completable.with(schedulerProvider: SchedulerProvider): Completable =
    observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun <T> Observable<T>.with(schedulerProvider: SchedulerProvider): Observable<T> =
    observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())