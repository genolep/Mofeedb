package com.bygen.mofeedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
data class SearchResponse(

	@field:SerializedName("results")
	val results: List<SearchResultsItem>,

)
data class SearchResultsItem(

	@field:SerializedName("media_type")
	val mediaType: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String?,

	@field:SerializedName("release_date")
	val releaseDate: String?,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("first_air_date")
	val firstAirDate: String?,


)

