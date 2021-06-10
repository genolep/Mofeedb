package com.bygen.mofeedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("genres")
	val genres: List<MovieTvGenre>,

	@field:SerializedName("runtime")
	val runtime: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double
)


