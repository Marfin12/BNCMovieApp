package com.example.bncmovieapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val year: String,
    val rating: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val desc: String,
    val starring: ArrayList<String>,
    val genre: String,
    val duration: String
) : Parcelable