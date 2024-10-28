package com.vinay.cricin.ui.matchdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinay.cricin.data.model.MatchDetails
import com.vinay.cricin.data.network.Constants
import com.vinay.cricin.data.network.Resource
import com.vinay.cricin.data.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    private val repository: MatchRepository

) : ViewModel() {
    private var _matchDetails = MutableStateFlow<MatchDetails?>(null)
    val matchDetails: StateFlow<MatchDetails?> = _matchDetails

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun fetchMatchDetails(matchId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            when(val result = repository.getMatchDetails(Constants.API_KEY, matchId)){
                is Resource.Success -> {
                    _matchDetails.value = result.data
                    _isLoading.value = false

                }
                is Resource.Error -> {
                    _errorMessage.value = result.message ?: "Unknown error occurred"
                    _isLoading.value = false

                }
            }

        }
    }
}