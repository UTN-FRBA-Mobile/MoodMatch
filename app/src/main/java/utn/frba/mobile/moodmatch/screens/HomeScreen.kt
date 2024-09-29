package utn.frba.mobile.moodmatch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Footer
import utn.frba.mobile.moodmatch.common.Header

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Backgroud())
        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
    ){
        Column {
            Header()
            // UserData()
            Title()
            //Subtitle()
            //RecommendationButtons()
            //RecomendationItems()
            Footer()

        }
    }
}