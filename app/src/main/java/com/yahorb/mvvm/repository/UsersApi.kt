package com.yahorb.mvvm.repository

import com.yahorb.mvvm.model.data.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UsersApi {

    @GET("/users")
    fun getUsers(): Observable<List<User>>
}