package com.vinay.cricin.ui.matchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinay.cricin.data.model.Match
import com.vinay.cricin.data.network.Constants
import com.vinay.cricin.data.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchListViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    private val _matchList = MutableStateFlow<List<Match>>(emptyList())
    val matchList: StateFlow<List<Match>> = _matchList
    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        viewModelScope.launch {
            _matchList.value = repository.getMatchList(Constants.API_KEY).data?: emptyList()
        }
    }
}