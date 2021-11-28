package com.example.bncmovieapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.bncmovieapp.core.data.Resource
import com.example.bncmovieapp.core.domain.model.Session
import com.example.bncmovieapp.core.domain.usecase.MovieUseCase

class MainViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val loginAdapter = ArrayList<LiveData<Resource<Session>>>()
    val sessionAdapter = movieUseCase.getUserSession().asLiveData()

    fun endSession(session: Session) = movieUseCase.endSession(session)

    fun initLoginAdapter() =
        loginAdapter.add(
            movieUseCase.postUser("", "").asLiveData()
        )

    fun generateLoginAdapter(email: String, password: String) =
        loginAdapter.set(0,
            movieUseCase.postUser(email, password).asLiveData()
        )
}