package com.bygen.mofeedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
	@SerializedName("overview")
	val overview: String,

	@SerializedName("release_date")
	val releaseDate: String,

	@SerializedName("genres")
	val genres: List<MovieTvGenre>,

	@SerializedName("runtime")
	val runtime: Int,

	@SerializedName("id")
	val id: Int,

	@SerializedName("tagline")
	val tagline: String,

	@SerializedName("title")
	val title: String,

	@SerializedName("poster_path")
	val posterPath: String,

	@SerializedName("vote_average")
	val voteAverage: Double
)


