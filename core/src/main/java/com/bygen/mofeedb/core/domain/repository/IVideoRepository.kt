package com.bygen.mofeedb.core.domain.repository

import com.bygen.mofeedb.core.data.source.Resource
import com.bygen.mofeedb.core.domain.model.MovieTv
import kotlinx.coroutines.flow.Flow

interface IVideoRepository {
    fun getMovie(): Flow<Resource<List<MovieTv>>>
    fun getTelevisions(): Flow<Resource<List<MovieTv>>>
    fun getDetailMovie(id:Int,fromSearch: Boolean): Flow<Resource<MovieTv>>
    fun getDetailTelevision(id:Int,fromSearch: Boolean): Flow<Resource<MovieTv>>

    fun getFavorite(): Flow<List<MovieTv>>

    fun setFavoriteMovie(movie: MovieTv, state: Boolean)
    fun setFavoriteTelevision(movie: MovieTv, state: Boolean)

    fun getSearch(query:String): Flow<Resource<List<MovieTv>>>


}