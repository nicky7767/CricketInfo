package com.vinay.cricin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vinay.cricin.navigation.Screen
import com.vinay.cricin.ui.matchdetail.MatchDetailScreen
import com.vinay.cricin.ui.matchlist.MatchListScreen
import com.vinay.cricin.ui.theme.CricInTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CricInTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.MatchListScreen.route
                ) {
                    composable(Screen.MatchListScreen.route) {
                        MatchListScreen(navController = navController)
                    }
                    composable(
                        "${Screen.MatchDetailScreen.route}/{matchId}",
                        listOf(navArgument("matchId") { type = NavType.StringType })
                    ) {
                        backStackEntry ->
                        val matchId = backStackEntry.arguments?.getString("matchId")
                        if (matchId != null) {
                            MatchDetailScreen(matchId = matchId, navController)
                        }
                    }
                }
            }
        }
    }
}

