package com.example.bncmovieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val detailResponse: DetailResponse
)