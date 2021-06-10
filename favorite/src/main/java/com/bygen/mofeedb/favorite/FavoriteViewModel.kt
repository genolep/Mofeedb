package com.bygen.mofeedb.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bygen.mofeedb.core.domain.usecase.VideoUseCase

class FavoriteViewModel(videoUseCase: VideoUseCase) : ViewModel() {

    val favorite = videoUseCase.getFavorite().asLiveData()
}