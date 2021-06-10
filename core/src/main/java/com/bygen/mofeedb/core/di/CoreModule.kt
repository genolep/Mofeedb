package com.bygen.mofeedb.core.di

import androidx.room.Room
import com.bygen.mofeedb.core.data.source.VideoRepository
import com.bygen.mofeedb.core.data.source.local.LocalDataSource
import com.bygen.mofeedb.core.data.source.local.room.VideoDatabase
import com.bygen.mofeedb.core.data.source.remote.RemoteDataSource
import com.bygen.mofeedb.core.data.source.remote.network.ApiService
import com.bygen.mofeedb.core.domain.repository.IVideoRepository
import com.bygen.mofeedb.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<VideoDatabase>().videoDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            VideoDatabase::class.java, "video_table"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IVideoRepository> {
        VideoRepository(
            get(),
            get(),
            get()
        )
    }
}
