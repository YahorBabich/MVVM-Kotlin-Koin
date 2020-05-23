package com.yahorb.mvvm.network

import com.yahorb.mvvm.model.data.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UsersApi {

    @GET("/users")
    fun getUsers(): Observable<List<User>>
}