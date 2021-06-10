package com.bygen.mofeedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ResultsItemTelevision(

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    )

data class PopularTvResponse(

    @field:SerializedName("results")
    val results: List<ResultsItemTelevision>,
    )