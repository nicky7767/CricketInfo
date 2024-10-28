package com.vinay.cricin.ui.matchdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.vinay.cricin.data.model.MatchDetails
import com.vinay.cricin.utils.FormatDate

@Composable
fun MatchDetailScreen(
    matchId: String,
    navController: NavController,
    viewModel: MatchDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchMatchDetails(matchId)
    }
    val matchDetails by viewModel.matchDetails.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            isLoading -> {
                Text(text = "Loading...")
            }

            errorMessage.isNotEmpty() -> {
                Text(text = "Error: $errorMessage")
            }

            matchDetails != null -> {
                MatchDetailsContent(matchDetails = matchDetails!!)
            }
        }
    }

}

@Composable
fun MatchDetailsContent(matchDetails: MatchDetails) {
        Column(modifier = Modifier.padding(20.dp)) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = matchDetails.name,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = matchDetails.matchType.uppercase(), fontWeight = FontWeight.W600, fontSize = 25.sp)
                Text(text = FormatDate(matchDetails.dateTimeGMT), fontSize = 20.sp, color = Color(0xFF9E9AAD))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = matchDetails.venue, fontSize = 18.sp, color = Color(0xFF9E9AAD))
            Spacer(modifier = Modifier.height(60.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Column {
                    if(matchDetails.teamInfo[0].img != "") {
                        SubcomposeAsyncImage(
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(matchDetails.teamInfo[0].img)
                                .build(),
                            contentDescription = matchDetails.teamInfo[0].name,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = matchDetails.teams[0], fontSize = 20.sp, overflow = TextOverflow.Ellipsis)
                }
                Text(text = "VS", fontSize = 45.sp)
                Column {
                    if(matchDetails.teamInfo[1].img != "") {
                        SubcomposeAsyncImage(
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(matchDetails.teamInfo[1].img)
                                .build(),
                            contentDescription = matchDetails.teamInfo[1].name,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = matchDetails.teams[1], fontSize = 20.sp)
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = matchDetails.status, fontSize = 24.sp, color = Color(0xFF9E9AAD))
        }
}