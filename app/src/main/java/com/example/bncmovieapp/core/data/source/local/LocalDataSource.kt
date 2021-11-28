package com.example.bncmovieapp.core.data.source.local

import com.example.bncmovieapp.core.data.source.local.entity.MovieEntity
import com.example.bncmovieapp.core.data.source.local.entity.SessionEntity
import com.example.bncmovieapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val movieDao: MovieDao) {

    fun getUserSession(): Flow<SessionEntity> = movieDao.getUserSession()

    fun removeUserSession(session: SessionEntity) {
        movieDao.clearSession(session)
    }

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun getMovieById(id: String): Flow<MovieEntity> = movieDao.getMovieById(id)

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    suspend fun insertSession(session: SessionEntity) = movieDao.insertSession(session)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

    fun updateMovie(movie: MovieEntity) {
        movieDao.updateFavoriteMovie(movie)
    }
}