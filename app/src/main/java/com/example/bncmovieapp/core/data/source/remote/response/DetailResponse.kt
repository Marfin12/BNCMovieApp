package com.example.bncmovieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @field:SerializedName("id")
    var id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("year")
    val year: String,

    @field:SerializedName("rating")
    val rating: String,

    @field:SerializedName("imageUrl")
    val imageUrl: String,

    @field:SerializedName("desc")
    val desc: String,

    @field:SerializedName("starring")
    val starring: ArrayList<String>,

    @field:SerializedName("genre")
    val genre: String,

    @field:SerializedName("duration")
    val duration: String
)