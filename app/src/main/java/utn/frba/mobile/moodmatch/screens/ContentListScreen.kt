package utn.frba.mobile.moodmatch.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.data.model.Enterteinment

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContentListScreen(
    recommendationList: List<Enterteinment>
) {
    Scaffold(bottomBar = {}){
        Column {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                items(recommendationList) { recommendation ->
                    ItemsCard(
                        recommendation = recommendation
                    )
                }
            }
        }
    }
}

@Composable
fun ItemsCard(recommendation: Enterteinment) {
    Box() {
        Text(text = recommendation.name)
        Text(
            modifier = Modifier.padding(50.dp),
            text = stringResource(id = R.string.recomendaciones),
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            style = TextStyle(fontSize = 19.sp),
        )
    }
}
