package com.vinay.cricin.data.model

data class MatchDetails(
    val bbbEnabled: Boolean,
    val date: String,
    val dateTimeGMT: String,
    val fantasyEnabled: Boolean,
    val hasSquad: Boolean,
    val id: String,
    val matchEnded: Boolean,
    val matchStarted: Boolean,
    val matchType: String,
    val matchWinner: String = "N/A",
    val name: String,
    val score: List<Score>,
    val seriesId: String,
    val status: String,
    val teamInfo: List<TeamInfo> = listOf(TeamInfo("", "",""), TeamInfo("", "","")),
    val teams: List<String>,
    val tossChoice: String,
    val tossWinner: String,
    val venue: String
)

data class MatchDetailsResponse(
    val data: MatchDetails
)