package com.example.bncmovieapp.core.data.source.remote.network

import com.example.bncmovieapp.core.data.source.remote.response.DetailMovieResponse
import com.example.bncmovieapp.core.data.source.remote.response.ListMovieResponse
import com.example.bncmovieapp.core.data.source.remote.response.UserDataResponse
import com.example.bncmovieapp.core.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("movies")
    suspend fun getMovies(): ListMovieResponse

    @GET("movies/{id}")
    suspend fun getMoviesById(@Path("id") id: String): DetailMovieResponse

    @POST("login")
    suspend fun postUser(@Body user: User): UserDataResponse
}