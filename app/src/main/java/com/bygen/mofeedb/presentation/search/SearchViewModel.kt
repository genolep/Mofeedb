package com.bygen.mofeedb.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bygen.mofeedb.core.domain.usecase.VideoUseCase

class SearchViewModel(videoUseCase: VideoUseCase) : ViewModel() {
    private val query = MutableLiveData<String>()
    fun setSearch(querySet: String) {
        this.query.value = querySet
    }

    var search = Transformations.switchMap(query) { query ->
        videoUseCase.getSearch(
            query
        ).asLiveData()
    }
}