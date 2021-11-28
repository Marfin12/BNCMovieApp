package com.example.bncmovieapp.di

import com.example.bncmovieapp.main.MainViewModel
import com.example.bncmovieapp.core.domain.usecase.MovieInteractor
import com.example.bncmovieapp.core.domain.usecase.MovieUseCase
import com.example.bncmovieapp.favorite.FavoriteViewModel
import com.example.bncmovieapp.home.HomeViewModel
import com.example.bncmovieapp.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { MainViewModel(get()) }
}