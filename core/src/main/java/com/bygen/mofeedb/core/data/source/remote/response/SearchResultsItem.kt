package com.bygen.mofeedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
data class SearchResponse(

	@SerializedName("results")
	val results: List<SearchResultsItem>,

)
data class SearchResultsItem(

	@SerializedName("media_type")
	val mediaType: String,

	@SerializedName("name")
	val name: String,

	@SerializedName("id")
	val id: Int,

	@SerializedName("overview")
	val overview: String,

	@SerializedName("title")
	val title: String,

	@SerializedName("poster_path")
	val posterPath: String?,

	@SerializedName("release_date")
	val releaseDate: String?,

	@SerializedName("vote_average")
	val voteAverage: Double,

	@SerializedName("first_air_date")
	val firstAirDate: String?,


)

