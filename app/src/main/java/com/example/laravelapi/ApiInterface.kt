package com.example.laravelapi

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @POST("register")
    fun register(@Body user: User): Call<User>

    @POST("login")
    fun login(@Body login: LoginResponse): Call<LoginResponse>
}