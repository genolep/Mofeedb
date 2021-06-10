package com.bygen.mofeedb.presentation.di

import com.bygen.mofeedb.core.domain.usecase.VideoInteractor
import com.bygen.mofeedb.core.domain.usecase.VideoUseCase
import com.bygen.mofeedb.presentation.detail.DetailMovieViewModel
import com.bygen.mofeedb.presentation.popular.mov.MovieViewModel
import com.bygen.mofeedb.presentation.popular.tv.TelevisionViewModel
import com.bygen.mofeedb.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<VideoUseCase> {
        VideoInteractor(get())
    }
}
val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TelevisionViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { SearchViewModel(get()) }


}
