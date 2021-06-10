package com.bygen.mofeedb.core.data.source.local.room

import androidx.room.*
import com.bygen.mofeedb.core.data.source.local.entity.MovieTvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Query("SELECT * FROM video_table WHERE movie = 1 and fromSearch = 0")
    fun getMovie(): Flow<List<MovieTvEntity>>
    @Query("SELECT * FROM video_table WHERE movie = 0 and fromSearch = 0")
    fun getTelevision(): Flow<List<MovieTvEntity>>

    @Query("SELECT * FROM video_table WHERE favorite = 1 ORDER BY movie ASC ")
    fun getFavorite(): Flow<List<MovieTvEntity>>

    @Query("SELECT * FROM video_table WHERE videoId = :id")
    fun getDetailById(id:Int): Flow<MovieTvEntity>

    @Update
    fun updateState(movieTv: MovieTvEntity)
    @Update
    suspend fun updateDetail(movieTv: MovieTvEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVideos(video: List<MovieTvEntity>)

    @Query("DELETE FROM video_table WHERE fromSearch = 1 AND favorite= 0")
    suspend fun deleteSearch()

    @Query("SELECT * FROM video_table WHERE title LIKE :query")
    fun getSearch(query:String): Flow<List<MovieTvEntity>>

}