package com.example.bncmovieapp.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bncmovieapp.core.domain.model.Movie
import com.example.bncmovieapp.core.domain.usecase.MovieUseCase
import com.example.bncmovieapp.movie.MovieActivity.Companion.MOVIE_ID

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getMovieById(MOVIE_ID).asLiveData()
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}