package com.vinay.cricin.ui.matchlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vinay.cricin.data.model.Match
import com.vinay.cricin.navigation.Screen
import com.vinay.cricin.utils.FormatDate

@Composable
fun MatchListScreen(navController: NavController) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Cricket Info",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            MatchList(navController = navController)
        }
    }
}

@Composable
fun MatchList(
    navController: NavController,
    viewModel: MatchListViewModel = hiltViewModel()
) {
    val matchList by viewModel.matchList.collectAsState()
    LazyColumn {
        items(matchList) { match ->
            MatchEntry(match = match) {
                navController.navigate("${Screen.MatchDetailScreen.route}/${match.id}")
            }
        }
    }
}

@Composable
fun MatchEntry(
    match: Match,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1D1C3B))
            .shadow(
                2.dp,
                RoundedCornerShape(5.dp)
            )
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = match.matchType.uppercase(), fontWeight = FontWeight.W600, fontSize = 25.sp)

                Text(text = FormatDate(match.dateTimeGMT), fontSize = 18.sp, color = Color(0xFF9E9AAD))
            }
            Row {
                Text(text = match.teams[0])
            }
            Row {
                Text(text = match.teams[1])
            }
            Text(text = match.status, fontSize = 18.sp, color = Color(0xFF9E9AAD))
        }
    }
}