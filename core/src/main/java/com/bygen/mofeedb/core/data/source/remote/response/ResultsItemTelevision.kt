package com.bygen.mofeedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ResultsItemTelevision(

    @SerializedName("first_air_date")
    val firstAirDate: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: Int,

    )

data class PopularTvResponse(

    @SerializedName("results")
    val results: List<ResultsItemTelevision>,
    )