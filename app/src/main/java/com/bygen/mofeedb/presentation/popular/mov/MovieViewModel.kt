package com.bygen.mofeedb.presentation.popular.mov

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bygen.mofeedb.core.domain.usecase.VideoUseCase

class MovieViewModel(videoUseCase: VideoUseCase) : ViewModel() {

    val movie = videoUseCase.getMovie().asLiveData()
}