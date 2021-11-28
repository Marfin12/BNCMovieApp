package com.example.bncmovieapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bncmovieapp.core.domain.model.Movie
import com.example.bncmovieapp.core.domain.usecase.MovieUseCase

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovies().asLiveData()
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}