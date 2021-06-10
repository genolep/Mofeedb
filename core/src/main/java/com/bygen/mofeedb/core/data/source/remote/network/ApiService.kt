package com.bygen.mofeedb.core.data.source.remote.network

import com.bygen.mofeedb.core.BuildConfig
import com.bygen.mofeedb.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular?api_key=${BuildConfig.TMDB_TOKEN}")
    suspend fun getPopularMovie(): PopularMovieResponse

    @GET("tv/popular?api_key=${BuildConfig.TMDB_TOKEN}")
    suspend fun getPopularTelevision(): PopularTvResponse


    @GET("movie/{movie_id}?api_key=${BuildConfig.TMDB_TOKEN}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int
    ): MovieResponse

    @GET("tv/{tv_id}?api_key=${BuildConfig.TMDB_TOKEN}")
    suspend fun getTelevision(
        @Path("tv_id") televisionId: Int
    ): TelevisionResponse

    @GET("search/multi?api_key=${BuildConfig.TMDB_TOKEN}")
    suspend fun getSearch(@Query("query") search: String): SearchResponse
}