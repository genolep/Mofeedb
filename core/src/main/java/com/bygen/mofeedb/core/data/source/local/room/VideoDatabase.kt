package com.bygen.mofeedb.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bygen.mofeedb.core.data.source.local.entity.MovieTvEntity

@Database(entities = [MovieTvEntity::class], version = 2, exportSchema = false)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao

}