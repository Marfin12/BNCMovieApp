package com.example.bncmovieapp.core.data.source.local.room

import androidx.room.*
import com.example.bncmovieapp.core.data.source.local.entity.MovieEntity
import com.example.bncmovieapp.core.data.source.local.entity.SessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where id = :id")
    fun getMovieById(id: String): Flow<MovieEntity>

    @Query("SELECT * FROM session")
    fun getUserSession(): Flow<SessionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Delete
    fun clearSession(session: SessionEntity)
}