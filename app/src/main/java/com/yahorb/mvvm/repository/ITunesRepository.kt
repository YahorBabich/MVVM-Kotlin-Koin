package com.yahorb.mvvm.repository

import com.yahorb.mvvm.model.data.Term
import io.reactivex.disposables.Disposable

interface ITunesRepository {

    fun search(
        term: String,
        onSuccess: (Term) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable
}