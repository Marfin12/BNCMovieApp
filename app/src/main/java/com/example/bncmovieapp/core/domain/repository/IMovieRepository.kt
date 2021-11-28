package com.example.bncmovieapp.core.domain.repository

import com.example.bncmovieapp.core.data.Resource
import com.example.bncmovieapp.core.domain.model.Movie
import com.example.bncmovieapp.core.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun getMovieById(movie: Movie): Flow<Resource<Movie>>

    fun getUserSession(): Flow<Session>

    fun postUser(email: String, password: String): Flow<Resource<Session>>

    fun endSession(session: Session)
}