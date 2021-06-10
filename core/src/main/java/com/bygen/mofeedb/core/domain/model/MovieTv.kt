package com.bygen.mofeedb.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieTv(
    var videoId: Int,
    var title: String,
    var imageUrl: String,
    var release: String,
    var overview: String,
    var genre: String,
    var tagline: String,
    var duration: Int,
    var vote: Double,
    var isMovie: Boolean = false,
    var isFavorite: Boolean = false,
    var fromSearch: Boolean = false
) : Parcelable
