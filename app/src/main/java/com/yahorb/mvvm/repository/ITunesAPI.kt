package com.yahorb.mvvm.repository

import com.yahorb.mvvm.model.data.Term
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesAPI {
    @GET("/search")
    fun search(@Query("term") term: String): Observable<Term>
}
