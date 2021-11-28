package com.example.bncmovieapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bncmovieapp.core.data.source.local.entity.MovieEntity
import com.example.bncmovieapp.core.data.source.local.entity.SessionEntity

@Database(entities = [MovieEntity::class, SessionEntity::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}