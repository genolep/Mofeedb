package com.bygen.mofeedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ResultsItemMovie(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("id")
    val id: Int,
)
data class PopularMovieResponse(


    @field:SerializedName("results")
    val results: List<ResultsItemMovie>,

)
