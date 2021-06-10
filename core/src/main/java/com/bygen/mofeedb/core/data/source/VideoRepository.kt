package com.bygen.mofeedb.core.data.source

import com.bygen.mofeedb.core.data.source.local.LocalDataSource
import com.bygen.mofeedb.core.data.source.remote.RemoteDataSource
import com.bygen.mofeedb.core.data.source.remote.network.ApiResponse
import com.bygen.mofeedb.core.data.source.remote.response.*
import com.bygen.mofeedb.core.domain.model.MovieTv
import com.bygen.mofeedb.core.domain.repository.IVideoRepository
import com.bygen.mofeedb.core.utils.AppExecutors
import com.bygen.mofeedb.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VideoRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IVideoRepository {

    override fun getMovie(): Flow<Resource<List<MovieTv>>> =
        object : NetworkBoundResource<List<MovieTv>, List<ResultsItemMovie>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> {
                return localDataSource.getMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieTv>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemMovie>>> =
                remoteDataSource.getPopularMovie()


            override suspend fun saveCallResult(data: List<ResultsItemMovie>) {
                val movieList = DataMapper.mapPopularMoviesToEntities(data, false)
                localDataSource.insertVideos(movieList)
            }
        }.asFlow()


    override fun getTelevisions(): Flow<Resource<List<MovieTv>>> =
        object : NetworkBoundResource<List<MovieTv>, List<ResultsItemTelevision>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> {
                return localDataSource.getTelevision().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieTv>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemTelevision>>> =
                remoteDataSource.getPopularTelevision()

            override suspend fun saveCallResult(data: List<ResultsItemTelevision>) {
                val televisionList = DataMapper.mapPopularTelevisionToEntities(data, false)
                localDataSource.insertVideos(televisionList)

            }
        }.asFlow()


    override fun getDetailMovie(id: Int,fromSearch: Boolean): Flow<Resource<MovieTv>> =
        object : NetworkBoundResource<MovieTv, MovieResponse>() {
            override fun loadFromDB(): Flow<MovieTv> {
                return localDataSource.getDetailById(id).map {
                    DataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: MovieTv?): Boolean =
                data?.duration == 0

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovie(id)


            override suspend fun saveCallResult(data: MovieResponse) {
                val movie = DataMapper.mapMovieResponseToEntities(data,fromSearch)
                localDataSource.updateDetail(movie)
            }

        }.asFlow()


    override fun getDetailTelevision(id: Int,fromSearch: Boolean): Flow<Resource<MovieTv>> =
        object : NetworkBoundResource<MovieTv, TelevisionResponse>() {
            override fun loadFromDB(): Flow<MovieTv> {
                return localDataSource.getDetailById(id).map {
                    DataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: MovieTv?): Boolean =
                data?.duration == 0


            override suspend fun createCall(): Flow<ApiResponse<TelevisionResponse>> =
                remoteDataSource.getTelevision(id)


            override suspend fun saveCallResult(data: TelevisionResponse) {
                val television = DataMapper.mapTelevisionResponseToEntities(data,fromSearch)
                localDataSource.updateDetail(television)
            }

        }.asFlow()


    override fun getFavorite(): Flow<List<MovieTv>> {
        return localDataSource.getFavorite().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: MovieTv, state: Boolean) {
        val movieTvEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavorite(movieTvEntity, state) }
    }

    override fun setFavoriteTelevision(movie: MovieTv, state: Boolean) {
        val movieTvEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavorite(movieTvEntity, state) }
    }

    override fun getSearch(query: String): Flow<Resource<List<MovieTv>>> {
        return object : NetworkBoundResource<List<MovieTv>, List<SearchResultsItem>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> {
                return localDataSource.getSearch(query).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }
            override fun shouldFetch(data: List<MovieTv>?): Boolean = true
            override suspend fun createCall(): Flow<ApiResponse<List<SearchResultsItem>>> {
                return remoteDataSource.getSearch(query)
            }


            override suspend fun saveCallResult(data: List<SearchResultsItem>) {
                val result = DataMapper.mapDivideResponseToEntities(data)
                localDataSource.deleteSearch()
                localDataSource.insertVideos(result)
            }

        }.asFlow()
    }

}


