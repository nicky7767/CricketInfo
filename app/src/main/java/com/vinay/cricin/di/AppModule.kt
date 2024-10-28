package com.vinay.cricin.di

import com.vinay.cricin.data.network.ApiService
import com.vinay.cricin.data.network.Constants
import com.vinay.cricin.data.repository.MatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        matchApi: ApiService
    ) = MatchRepository(matchApi)

    @Singleton
    @Provides
    fun provideMatchApi(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

}