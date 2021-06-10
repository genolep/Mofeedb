package com.bygen.mofeedb.core.domain.usecase

import com.bygen.mofeedb.core.data.source.Resource
import com.bygen.mofeedb.core.domain.model.MovieTv
import com.bygen.mofeedb.core.domain.repository.IVideoRepository
import kotlinx.coroutines.flow.Flow

class VideoInteractor(private val videoRepository: IVideoRepository): VideoUseCase {
    override fun getMovie() = videoRepository.getMovie()

    override fun getTelevisions() = videoRepository.getTelevisions()

    override fun getDetailMovie(id: Int,fromSearch: Boolean) = videoRepository.getDetailMovie(id,fromSearch)

    override fun getDetailTelevision(id: Int,fromSearch: Boolean) = videoRepository.getDetailTelevision(id,fromSearch)

    override fun getFavorite() = videoRepository.getFavorite()

    override fun setFavoriteMovie(movie: MovieTv, state: Boolean) = videoRepository.setFavoriteMovie(movie,state)

    override fun setFavoriteTelevision(movie: MovieTv, state: Boolean) = videoRepository.setFavoriteTelevision(movie,state)

    override fun getSearch(query: String): Flow<Resource<List<MovieTv>>> = videoRepository.getSearch(query)
}