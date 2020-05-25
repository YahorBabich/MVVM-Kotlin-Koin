package com.yahorb.mvvm.repository.local

import com.yahorb.mvvm.model.data.Term
import io.reactivex.disposables.Disposable

interface ITuneRepository {

    fun search(
        term: String,
        onSuccess: (Term) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable
}