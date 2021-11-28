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
import org.junit.Assert.assertTrue
import org.junit.Test

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
    assertEquals(listMovieEntity, arrayListOf(movieEntity))
}

@Test fun mapDomainToEntity_EqualTo_MovieEntity() {
    val mockedMovieEntity = mapDomainToEntity(movie)
    assertEquals(mockedMovieEntity, movieEntity)
}

@Test fun mapSessionDomainToEntity_EqualTo_SessionEntity() {
    val mockedSessionEntity = mapSessionDomainToEntity(session)
    assertEquals(mockedSessionEntity, sessionEntity)
}

@Test fun mapSessionResponsesToEntities_EqualTo_SessionEntity() {

    assertTrue(mapSessionResponsesToEntities(sessionResponse) === sessionEntity)
}

@Test fun emailValidator_InvalidEmailNoUsername_ReturnsTrue() {
    assertTrue(mapDetailResponsesToEntities(detailResponse) === movieEntity)
}

@Test fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
    assertTrue(mapEntitiesToDomain(arrayListOf(movieEntity)) === arrayListOf(movie))
}

@Test fun emailValidator_InvalidEmailNoUsername_ReturnsFalse2() {
    assertTrue(mapSessionEntityToDomain(sessionEntity) === session)
}

@Test fun emailValidator_InvalidEmailNoUsername_ReturnsFalse3() {
    assertTrue(mapEntityToDomain(movieEntity) === movie)
}

@Test fun emailValidator_InvalidEmailNoUsername_ReturnsFalse4() {
    assertTrue(mapNothingSessionToDomain() === Session(token = ""))
}
