package com.bygen.mofeedb.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bygen.mofeedb.core.domain.usecase.VideoUseCase

class DetailMovieViewModel(private val videoUseCase: VideoUseCase) : ViewModel() {

    private val movieId = MutableLiveData<Pair<Int,Boolean>>()
    private val televisionId = MutableLiveData<Pair<Int,Boolean>>()

    fun setSelectedMovie(movieId: Int,isSearch:Boolean) {
        this.movieId.value = (movieId to isSearch)
    }

    fun setSelectedTelevision(televisionId: Int,isSearch: Boolean) {
        this.televisionId.value = (televisionId to isSearch)
    }

    var detailMovie =
        Transformations.switchMap(movieId) { videoId ->
            videoUseCase.getDetailMovie(
                videoId.first,videoId.second
            ).asLiveData()
        }

    var detailTelevision =
        Transformations.switchMap(televisionId) { videoId ->
            videoUseCase.getDetailTelevision(
                videoId.first,videoId.second
            ).asLiveData()
        }

    fun setFavoriteMov() {
        val movieResource = detailMovie.value
        if (movieResource != null) {
            val movieEntity = movieResource.data
            if (movieEntity != null) {
                val newState = !movieEntity.isFavorite
                videoUseCase.setFavoriteMovie(movieEntity, newState)
            }
        }


    }

    fun setFavoriteTv() {
        val televisionResource = detailTelevision.value
        if (televisionResource != null) {
            val televisionEntity = televisionResource.data
            if (televisionEntity != null) {
                val newState = !televisionEntity.isFavorite
                videoUseCase.setFavoriteTelevision(televisionEntity, newState)
            }

        }
    }

}