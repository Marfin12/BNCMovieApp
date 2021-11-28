package com.example.bncmovieapp.core.data

import com.example.bncmovieapp.core.data.source.local.LocalDataSource
import com.example.bncmovieapp.core.data.source.remote.RemoteDataSource
import com.example.bncmovieapp.core.data.source.remote.network.ApiResponse
import com.example.bncmovieapp.core.data.source.remote.response.DetailResponse
import com.example.bncmovieapp.core.data.source.remote.response.MovieResponse
import com.example.bncmovieapp.core.data.source.remote.response.UserResponse
import com.example.bncmovieapp.core.domain.model.Movie
import com.example.bncmovieapp.core.domain.model.Session
import com.example.bncmovieapp.core.domain.repository.IMovieRepository
import com.example.bncmovieapp.core.utils.AppExecutors
import com.example.bncmovieapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getMovieById(movie: Movie): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, DetailResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieById(movie.id).map {
                    DataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<DetailResponse>> =
                remoteDataSource.getMovieById(movie.id)

            override suspend fun saveCallResult(data: DetailResponse) {
                val movieEntity = DataMapper.mapMovieDomainToMovieDetailEntity(movie, data)
                appExecutors.diskIO().execute { localDataSource.updateMovie(movieEntity) }
            }
        }.asFlow()

    override fun postUser(email: String, password: String): Flow<Resource<Session>> =
        object : NetworkBoundResource<Session, UserResponse>() {
            override fun loadFromDB(): Flow<Session> {
                return localDataSource.getUserSession().map {
                    DataMapper.mapNothingSessionToDomain()
                }
            }

            override fun shouldFetch(data: Session?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<UserResponse>> =
                remoteDataSource.postUser(email, password)

            override suspend fun saveCallResult(data: UserResponse) {
                val userEntity = DataMapper.mapSessionResponsesToEntities(data)
                localDataSource.insertSession(userEntity)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getUserSession(): Flow<Session> {
        return localDataSource.getUserSession().map {
            if (it !== null) DataMapper.mapSessionEntityToDomain(it)
            else DataMapper.mapNothingSessionToDomain()
        }
    }

    override fun endSession(session: Session) {
        val sessionEntity = DataMapper.mapSessionDomainToEntity(session)
        appExecutors.diskIO().execute { localDataSource.removeUserSession(sessionEntity) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}