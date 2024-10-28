package com.vinay.cricin.navigation


sealed class Screen(val route: String) {
    data object MatchListScreen : Screen("match_list_screen")
    data object MatchDetailScreen : Screen("match_detail_screen")
}