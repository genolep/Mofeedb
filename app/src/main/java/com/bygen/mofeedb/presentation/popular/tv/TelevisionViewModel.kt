package com.bygen.mofeedb.presentation.popular.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bygen.mofeedb.core.domain.usecase.VideoUseCase

class TelevisionViewModel(videoUseCase: VideoUseCase) : ViewModel() {

    val television = videoUseCase.getTelevisions().asLiveData()
}