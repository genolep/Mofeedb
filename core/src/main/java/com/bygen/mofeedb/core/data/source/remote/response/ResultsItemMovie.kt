package com.bygen.mofeedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ResultsItemMovie(

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("id")
    val id: Int,
)
data class PopularMovieResponse(


    @SerializedName("results")
    val results: List<ResultsItemMovie>,

)
