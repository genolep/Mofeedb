package com.bygen.mofeedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TelevisionResponse(

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("genres")
    val genres: List<MovieTvGenre>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("tagline")
    val tagline: String,

    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double
)


