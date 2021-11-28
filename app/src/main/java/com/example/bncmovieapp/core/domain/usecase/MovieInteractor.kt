package com.example.bncmovieapp.core.domain.usecase

import com.example.bncmovieapp.core.domain.model.Movie
import com.example.bncmovieapp.core.domain.model.Session
import com.example.bncmovieapp.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovies() = movieRepository.getAllMovies()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

    override fun getMovieById(movie: Movie) = movieRepository.getMovieById(movie)

    override fun postUser(email: String, password: String) = movieRepository.postUser(email, password)

    override fun getUserSession() = movieRepository.getUserSession()

    override fun endSession(session: Session) = movieRepository.endSession(session)

}