package com.example.bncmovieapp.core.data.source.remote

import com.example.bncmovieapp.core.data.source.remote.network.ApiResponse
import com.example.bncmovieapp.core.data.source.remote.network.ApiService
import com.example.bncmovieapp.core.data.source.remote.response.DetailResponse
import com.example.bncmovieapp.core.data.source.remote.response.MovieResponse
import com.example.bncmovieapp.core.data.source.remote.response.UserResponse
import com.example.bncmovieapp.core.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.list
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieById(id: String): Flow<ApiResponse<DetailResponse>> {
        return flow {
            try {
                val response = apiService.getMoviesById(id)
                val data = response.detailResponse
                if (response.detailResponse.title.isNotEmpty()){
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postUser(email: String, password: String): Flow<ApiResponse<UserResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.postUser(User(email, password))
                val data = response.user
                if (response.user.token.isNotEmpty()){
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}