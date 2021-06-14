package com.bygen.mofeedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TelevisionResponse(

    @SerializedName("first_air_date")
    val firstAirDate: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("genres")
    val genres: List<MovieTvGenre>,

    @SerializedName("name")
    val name: String,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("vote_average")
    val voteAverage: Double
)


