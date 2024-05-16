package com.example.laravelapi

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("password_confirmation")
    val password_c: String
)