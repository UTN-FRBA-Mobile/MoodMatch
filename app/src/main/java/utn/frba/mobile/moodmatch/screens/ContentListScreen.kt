package utn.frba.mobile.moodmatch.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Recommendation
import utn.frba.mobile.moodmatch.common.getTypeName
import utn.frba.mobile.moodmatch.data.model.Book
import utn.frba.mobile.moodmatch.data.model.Enterteinment
import utn.frba.mobile.moodmatch.screens.viewmodel.MainViewModel
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContentListScreen(
    recommendationList: List<Recommendation>,
    viewModel: MainViewModel,
    navController: NavController
) {
    Scaffold(bottomBar = {}){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Backgroud())
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier,
                text = getTypeName(if (recommendationList.isEmpty()) "Recomendaciones" else recommendationList[0].type)
                    .uppercase(Locale.getDefault()),
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                style = TextStyle(fontSize = 20.sp),
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(recommendationList) { recommendation ->
                    ItemsList(
                        recommendation = recommendation,
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun ItemsList(
    recommendation: Recommendation,
    viewModel: MainViewModel,
    navController: NavController
) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    viewModel.setRecommendation(recommendation)
                    val title = viewModel.selectedRecommendation?.title
                    navController.navigate("information/$title")
                }
        ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = recommendation.image,
                contentDescription = "imagen descriptiva",
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .height(110.dp)
                    .aspectRatio(1f)
                    .padding(bottom = 5.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier,
                text = recommendation.title,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                style = TextStyle(fontSize = 16.sp),
            )
        }
    }
}
