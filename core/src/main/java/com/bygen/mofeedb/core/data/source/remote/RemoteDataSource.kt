package com.bygen.mofeedb.core.data.source.remote

import com.bygen.mofeedb.core.data.source.remote.network.ApiResponse
import com.bygen.mofeedb.core.data.source.remote.network.ApiService
import com.bygen.mofeedb.core.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovie(id: Int): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovie(id)
                if (response.id != 0) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTelevision(
        id: Int,
    ): Flow<ApiResponse<TelevisionResponse>> {
        return flow {
            try {
                val response = apiService.getTelevision(id)
                if (response.id != 0) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularMovie(): Flow<ApiResponse<List<ResultsItemMovie>>> {
        return flow {
            try {
                val response = apiService.getPopularMovie()
                val result = response.results
                if (result.isNotEmpty()) {
                    emit(ApiResponse.Success(result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularTelevision(): Flow<ApiResponse<List<ResultsItemTelevision>>> {
        return flow {
            try {
                val response = apiService.getPopularTelevision()
                val result = response.results
                if (result.isNotEmpty()) {
                    emit(ApiResponse.Success(result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getSearch(query:String): Flow<ApiResponse<List<SearchResultsItem>>>{
        return flow{
            try {
                val response = apiService.getSearch(query)
                val result = response.results
                if (result.isNotEmpty()){
                    emit(ApiResponse.Success(result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}