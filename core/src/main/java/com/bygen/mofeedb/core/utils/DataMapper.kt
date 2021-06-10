package com.bygen.mofeedb.core.utils

import com.bygen.mofeedb.core.data.source.local.entity.MovieTvEntity
import com.bygen.mofeedb.core.data.source.remote.response.*
import com.bygen.mofeedb.core.domain.model.MovieTv
import java.time.LocalDate
import java.time.format.DateTimeFormatter


object DataMapper {
    private const val baseImageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
    fun mapMovieResponseToEntities(input: MovieResponse,isSearch: Boolean) =
        MovieTvEntity(
            videoId = input.id,
            title = input.title,
            imageUrl = baseImageUrl + input.posterPath,
            release = dateConvert(input.releaseDate),
            overview = input.overview,
            genre = input.genres.joinToString { movieTvGenre: MovieTvGenre -> movieTvGenre.name },
            tagline = input.tagline,
            duration = input.runtime,
            vote = input.voteAverage,
            isMovie = true,
            fromSearch = isSearch
        )

    fun mapTelevisionResponseToEntities(input: TelevisionResponse,isSearch: Boolean) =
        MovieTvEntity(
            videoId = input.id,
            title = input.name,
            imageUrl = baseImageUrl + input.posterPath,
            release = dateConvert(input.firstAirDate),
            overview = input.overview,
            genre = input.genres.joinToString { movieTvGenre: MovieTvGenre -> movieTvGenre.name },
            tagline = input.tagline,
            duration = input.episodeRunTime.takeIf { it.isNotEmpty() }?.get(0) ?: 0,
            vote = input.voteAverage,
            isMovie = false,
            fromSearch = isSearch
        )

    fun mapPopularMoviesToEntities(input: List<ResultsItemMovie>,isSearch: Boolean): List<MovieTvEntity> {
        val movieList = ArrayList<MovieTvEntity>()
        input.map {
            val movie = MovieTvEntity(
                videoId = it.id,
                title = it.title,
                imageUrl = baseImageUrl + it.posterPath,
                release = dateConvert(it.releaseDate),
                overview = "",
                genre = "",
                tagline = "",
                duration = 0,
                vote = it.voteAverage,
                isMovie = true,
                fromSearch = isSearch
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapPopularTelevisionToEntities(input: List<ResultsItemTelevision>, isSearch: Boolean): List<MovieTvEntity> {
        val televisionList = ArrayList<MovieTvEntity>()
        input.map {
            val television = MovieTvEntity(
                videoId = it.id,
                title = it.name,
                imageUrl = baseImageUrl + it.posterPath,
                release = dateConvert(it.firstAirDate),
                overview = "",
                genre = "",
                tagline = "",
                duration = 0,
                vote = it.voteAverage,
                isMovie = false,
                fromSearch = isSearch
            )
            televisionList.add(television)
        }
        return televisionList
    }

    fun mapEntitiesToDomain(input: List<MovieTvEntity>): List<MovieTv> =
        input.map {
            MovieTv(
                videoId = it.videoId,
                title = it.title,
                imageUrl = it.imageUrl,
                release = it.release,
                overview = it.overview,
                genre = it.genre,
                tagline = it.tagline,
                duration = it.duration,
                vote = it.vote,
                isMovie = it.isMovie,
                isFavorite = it.isFavorite,
                fromSearch = it.fromSearch
            )

        }

    fun mapEntityToDomain(input: MovieTvEntity?): MovieTv {
        return MovieTv(
            videoId = input?.videoId ?:0,
            title = input?.title ?:"?",
            imageUrl = input?.imageUrl ?:"?",
            release = input?.release ?:"?",
            overview = input?.overview ?:"?",
            genre = input?.genre ?:"?",
            tagline = input?.tagline ?:"?",
            duration = input?.duration ?:0,
            vote = input?.vote ?:0.0,
            isMovie = input?.isMovie ?:false,
            isFavorite = input?.isFavorite ?:false,
            fromSearch = input?.fromSearch ?:true
        )
    }

    fun mapDomainToEntity(input: MovieTv) =
        MovieTvEntity(
            videoId = input.videoId,
            title = input.title,
            imageUrl = input.imageUrl,
            release = input.release,
            overview = input.overview,
            genre = input.genre,
            tagline = input.tagline,
            duration = input.duration,
            vote = input.vote,
            isMovie = input.isMovie,
            isFavorite = input.isFavorite,
            fromSearch = input.fromSearch

        )

    fun mapDivideResponseToEntities(input: List<SearchResultsItem>): List<MovieTvEntity> {
        val movie = ArrayList<ResultsItemMovie>()
        val television = ArrayList<ResultsItemTelevision>()
        val combine = ArrayList<MovieTvEntity>()
        input.forEach {
            if (it.mediaType == "movie") {
                movie.add(
                    ResultsItemMovie(
                        id = it.id,
                        title = it.title,
                        posterPath = it.posterPath.toString(),
                        releaseDate = it.releaseDate.toString(),
                        voteAverage = it.voteAverage
                    )
                )
            } else if (it.mediaType == "tv") {
                television.add(
                    ResultsItemTelevision(
                        id = it.id,
                        firstAirDate = it.firstAirDate.toString(),
                        posterPath = it.posterPath.toString(),
                        voteAverage = it.voteAverage,
                        name = it.name
                    )
                )
            }
        }
        combine.addAll(mapPopularMoviesToEntities(movie,true))
        combine.addAll(mapPopularTelevisionToEntities(television,true))
        return combine
    }

    private fun dateConvert(date: String): String {
        if (date == "" || date =="null") return ""
        val parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE)
        val formatDate = parsedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        return formatDate.toString()
    }
}



