package com.vinay.cricin.data.network

import com.vinay.cricin.data.model.MatchDetailsResponse
import com.vinay.cricin.data.model.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("currentMatches")
    suspend fun getMatchList(
        @Query("apikey") apikey: String
    ): MatchResponse

    @GET("match_info")
    suspend fun getMatchDetails(
        @Query("apikey") apikey: String,
        @Query("id") id: String
    ): MatchDetailsResponse
}