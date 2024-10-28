package com.vinay.cricin.data.repository

import com.vinay.cricin.data.model.Match
import com.vinay.cricin.data.model.MatchDetails
import com.vinay.cricin.data.network.ApiService
import com.vinay.cricin.data.network.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MatchRepository @Inject constructor(private val apiService: ApiService){
    suspend fun getMatchList(apikey: String) : Resource<List<Match>> {
        val response = try {
            apiService.getMatchList(apikey)
        } catch (e: Exception) {
            println(e)
            return Resource.Error(e.message ?: "An unknown error occurred.")
        }
        return Resource.Success(response.data)
    }
    suspend fun getMatchDetails(apikey: String, id: String): Resource<MatchDetails> {
        val response = try {
            apiService.getMatchDetails(apikey, id)
        } catch (e: Exception) {
            println(e)
            return Resource.Error(e.message ?: "An unknown error occurred.")
        }
        return Resource.Success(response.data)
    }
}