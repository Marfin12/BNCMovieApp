package com.example.bncmovieapp.unit_test

import com.example.bncmovieapp.core.data.source.local.entity.MovieEntity
import com.example.bncmovieapp.core.data.source.local.entity.SessionEntity
import com.example.bncmovieapp.core.data.source.remote.response.DetailResponse
import com.example.bncmovieapp.core.data.source.remote.response.MovieResponse
import com.example.bncmovieapp.core.data.source.remote.response.UserResponse
import com.example.bncmovieapp.core.domain.model.Movie
import com.example.bncmovieapp.core.domain.model.Session
import com.example.bncmovieapp.core.utils.DataMapper.mapDetailResponsesToEntities
import com.example.bncmovieapp.core.utils.DataMapper.mapDomainToEntity
import com.example.bncmovieapp.core.utils.DataMapper.mapEntitiesToDomain
import com.example.bncmovieapp.core.utils.DataMapper.mapEntityToDomain
import com.example.bncmovieapp.core.utils.DataMapper.mapNothingSessionToDomain
import com.example.bncmovieapp.core.utils.DataMapper.mapResponsesToEntities
import com.example.bncmovieapp.core.utils.DataMapper.mapSessionDomainToEntity
import com.example.bncmovieapp.core.utils.DataMapper.mapSessionEntityToDomain
import com.example.bncmovieapp.core.utils.DataMapper.mapSessionResponsesToEntities
import org.junit.Assert.assertEquals
import org.junit.Test

class DataMapperTest {

    val session = Session(
        token = "abcdefgh"
    )
    val sessionEntity = SessionEntity(
        token = "abcdefgh"
    )
    val sessionResponse = UserResponse(
        token = "abcdefgh"
    )
    val movieEntity = MovieEntity(
        "1",
        "movie_title",
        "2021",
        "4",
        "",
        "horror",
        "this is movie",
        arrayListOf(),
        "60 minute"
    )
    val movie = Movie(
        "1",
        "movie_title",
        "2021",
        "4",
        "",
        false,
        "this is movie",
        arrayListOf(),
        "horror",
        "60 minute"
    )
    val movieResponse = MovieResponse(
        "1",
        "movie_title",
        "2021",
        "4",
        ""
    )
    val mockedMovieEntity = MovieEntity(
        "1",
        "movie_title",
        "2021",
        "4",
        "",
        "",
        "",
        arrayListOf(),
        ""
    )
    val detailResponse = DetailResponse(
        "1",
        "movie_title",
        "2021",
        "4",
        "",
        "this is movie",
        arrayListOf(),
        "horror",
        "60 minute"
    )

    @Test
    fun mapResponsesToEntities_EqualTo_ListOfMovieEntity() {
        val listMovieEntity = mapResponsesToEntities(arrayListOf(movieResponse))
        assertEquals(listMovieEntity, arrayListOf(mockedMovieEntity))
    }

    @Test
    fun mapDomainToEntity_EqualTo_MovieEntity() {
        val mockedMovieEntity = mapDomainToEntity(movie)
        assertEquals(mockedMovieEntity, movieEntity)
    }

    @Test
    fun mapSessionDomainToEntity_EqualTo_SessionEntity() {
        val mockedSession = mapSessionDomainToEntity(session)
        assertEquals(mockedSession, sessionEntity)
    }

    @Test
    fun mapSessionResponsesToEntities_EqualTo_SessionEntity() {
        val mockedSessionResponse = mapSessionResponsesToEntities(sessionResponse)
        assertEquals(mockedSessionResponse, sessionEntity)
    }

    @Test
    fun mapDetailResponsesToEntities_EqualTo_MovieEntity() {
        val mockedDetailResponse = mapDetailResponsesToEntities(detailResponse)
        assertEquals(mockedDetailResponse, movieEntity)
    }

    @Test
    fun mapSessionEntityToDomain_EqualTo_Session() {
        val mockedSessionEntity = mapSessionEntityToDomain(sessionEntity)
        assertEquals(mockedSessionEntity, session)
    }

    @Test
    fun mapEntitiesToDomain_EqualTo_ListOfMovie() {
        val mockedMovieEntity = mapEntitiesToDomain(arrayListOf(movieEntity))
        assertEquals(mockedMovieEntity, arrayListOf(movie))
    }

    @Test
    fun mapEntityToDomain_EqualTo_MovieEntity() {
        val mockedMovie = mapEntityToDomain(movieEntity)
        assertEquals(mockedMovie, movie)
    }

    @Test
    fun mapNothingSessionToDomain_EqualTo_MovieEntity() {
        assertEquals(mapNothingSessionToDomain(), Session(token = ""))
    }
}