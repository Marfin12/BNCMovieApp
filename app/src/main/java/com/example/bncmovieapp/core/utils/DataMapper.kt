package com.example.bncmovieapp.core.utils

import com.example.bncmovieapp.core.data.source.local.entity.MovieEntity
import com.example.bncmovieapp.core.data.source.local.entity.SessionEntity
import com.example.bncmovieapp.core.data.source.remote.response.DetailResponse
import com.example.bncmovieapp.core.data.source.remote.response.MovieResponse
import com.example.bncmovieapp.core.data.source.remote.response.UserResponse
import com.example.bncmovieapp.core.domain.model.Movie
import com.example.bncmovieapp.core.domain.model.Session

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                year = it.year,
                rating = it.rating,
                imageUrl = it.imageUrl,
                desc = "",
                genre = "",
                duration = "",
                starring = arrayListOf(),
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapDetailResponsesToEntities(input: DetailResponse) = (
            MovieEntity(
                id = input.id,
                title = input.title,
                year = input.year,
                rating = input.rating,
                imageUrl = input.imageUrl,
                desc = input.desc,
                genre = input.genre,
                starring = input.starring,
                duration = input.duration,
                isFavorite = false
            )
    )

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                year = it.year,
                rating = it.rating,
                imageUrl = it.imageUrl,
                desc = it.desc,
                genre = it.genre,
                duration = it.duration,
                starring = it.starring,
                isFavorite = it.isFavorite,
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        year = input.year,
        rating = input.rating,
        imageUrl = input.imageUrl,
        desc = input.desc,
        genre = input.genre,
        starring = input.starring,
        duration = input.duration,
        isFavorite = input.isFavorite
    )

    fun mapMovieDomainToMovieDetailEntity(input: Movie, resp: DetailResponse) = MovieEntity(
        id = input.id,
        title = input.title,
        year = input.year,
        rating = input.rating,
        imageUrl = input.imageUrl,
        desc = resp.desc,
        genre = resp.genre,
        starring = resp.starring,
        duration = resp.duration,
        isFavorite = input.isFavorite
    )

    fun mapEntityToDomain(input: MovieEntity) = Movie(
        id = input.id,
        title = input.title,
        year = input.year,
        rating = input.rating,
        imageUrl = input.imageUrl,
        desc = input.desc,
        genre = input.genre,
        duration = input.duration,
        starring = input.starring,
        isFavorite = input.isFavorite
    )

    fun mapSessionResponsesToEntities(input: UserResponse) = (
            SessionEntity(
                token = input.token
            ))

    fun mapSessionEntityToDomain(input: SessionEntity) = Session(
        token = input.token
    )

    fun mapSessionDomainToEntity(input: Session) = SessionEntity(
        token = input.token
    )

    fun mapNothingSessionToDomain() = Session(
        token = ""
    )
}