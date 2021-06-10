package com.bygen.mofeedb.core.data.source.local

import com.bygen.mofeedb.core.data.source.local.entity.MovieTvEntity
import com.bygen.mofeedb.core.data.source.local.room.VideoDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mVideoDao: VideoDao) {

    fun getMovie(): Flow<List<MovieTvEntity>> = mVideoDao.getMovie()

    fun getTelevision(): Flow<List<MovieTvEntity>> = mVideoDao.getTelevision()

    fun getDetailById(id: Int): Flow<MovieTvEntity> = mVideoDao.getDetailById(id)

    suspend fun updateDetail(movieTvEntity: MovieTvEntity){
        mVideoDao.updateDetail(movieTvEntity)
    }

    suspend fun insertVideos(videos: List<MovieTvEntity>) {
        mVideoDao.insertVideos(videos)
    }

    fun getFavorite(): Flow<List<MovieTvEntity>> = mVideoDao.getFavorite()


    fun setFavorite(movieTvEntity: MovieTvEntity, newState: Boolean) {
        movieTvEntity.isFavorite = newState
        mVideoDao.updateState(movieTvEntity)
    }
    suspend fun deleteSearch() = mVideoDao.deleteSearch()

    fun getSearch(query:String): Flow<List<MovieTvEntity>> = mVideoDao.getSearch(query)

}