package com.example.bncmovieapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "year")
    var year: String,

    @ColumnInfo(name = "rating")
    var rating: String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "desc")
    var desc: String,

    @ColumnInfo(name = "starring")
    var starring: ArrayList<String>,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)