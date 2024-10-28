package com.vinay.cricin.data.model

data class Match(
    val bbbEnabled: Boolean,
    val date: String,
    val dateTimeGMT: String,
    val fantasyEnabled: Boolean,
    val hasSquad: Boolean,
    val id: String,
    val matchEnded: Boolean,
    val matchStarted: Boolean,
    val matchType: String,
    val name: String,
    val score: List<Score>,
    val seriesId: String,
    val status: String,
    val teamInfo: List<TeamInfo>?,
    val teams: List<String>,
    val venue: String
)

data class MatchResponse(
    val data: List<Match>
)