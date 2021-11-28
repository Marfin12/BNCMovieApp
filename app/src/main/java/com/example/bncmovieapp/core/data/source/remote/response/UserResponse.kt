package com.example.bncmovieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("token")
    val token: String
)