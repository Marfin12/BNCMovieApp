package com.example.bncmovieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("year")
    val year: String,

    @field:SerializedName("rating")
    val rating: String,

    @field:SerializedName("imageUrl")
    val imageUrl: String,
)