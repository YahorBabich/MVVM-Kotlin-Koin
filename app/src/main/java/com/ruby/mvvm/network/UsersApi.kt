package com.ruby.mvvm.network

import com.ruby.mvvm.model.data.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UsersApi {

    @GET("/users")
    fun getUsers(): Observable<List<User>>
}