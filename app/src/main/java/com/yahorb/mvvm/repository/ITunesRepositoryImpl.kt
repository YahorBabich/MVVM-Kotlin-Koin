package com.yahorb.mvvm.repository

import com.yahorb.mvvm.extension.with
import com.yahorb.mvvm.model.data.Term
import com.yahorb.mvvm.repository.local.ITuneRepository
import com.yahorb.mvvm.util.rx.SchedulerProvider
import io.reactivex.disposables.Disposable

class ITunesRepositoryImpl(
    private val iTunesAPI: ITunesAPI,
    private val scheduler: SchedulerProvider
) : ITuneRepository {

    override fun search(
        term: String,
        onSuccess: (Term) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return iTunesAPI.search(term)
            .with(scheduler)
            .subscribe(onSuccess, onError)
    }
}