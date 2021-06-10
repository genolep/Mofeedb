package com.bygen.mofeedb.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_table")
data class MovieTvEntity(
    @PrimaryKey
    @NonNull
    var videoId: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "image_url")
    var imageUrl: String,
    @ColumnInfo(name = "release")
    var release: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "genre")
    var genre: String,
    @ColumnInfo(name = "tagline")
    var tagline: String,
    @ColumnInfo(name = "duration")
    var duration: Int,
    @ColumnInfo(name = "vote")
    var vote: Double,
    @ColumnInfo(name = "movie")
    var isMovie: Boolean = false,
    @ColumnInfo(name = "favorite")
    var isFavorite: Boolean = false,
    @ColumnInfo(name = "fromSearch")
    var fromSearch: Boolean = false
)
