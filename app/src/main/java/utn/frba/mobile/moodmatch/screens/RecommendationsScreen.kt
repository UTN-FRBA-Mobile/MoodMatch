package utn.frba.mobile.moodmatch.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Header
import utn.frba.mobile.moodmatch.common.Mood
import utn.frba.mobile.moodmatch.common.RecommendationCarousel
import utn.frba.mobile.moodmatch.screens.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecommendationScreen(
    emocion: Mood,
    navController: NavController,
    viewModel: MainViewModel
) {
    val recommendations by viewModel.recommendations.collectAsState()
    val isLoading by viewModel.isLoading

    // Lanzamos la llamada a la API en un efecto
    LaunchedEffect(emocion) {
        viewModel.getRecommendations(emocion)
    }

    Log.d("RecommendationsScreen", "Recommendations fetched: $recommendations")

    Scaffold{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Backgroud()), // Cambia este color según tu fondo
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header con imagen de perfil, logo y ícono de menú
                Header()

                // Texto de recomendación
                Text(
                    text = stringResource(R.string.recommendation_screen_greet_esp),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                MoodSection(mood = emocion ?: Mood.NEUTRAL)

                // Mostrar cargador si está cargando
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    // Mostrar RecommendationsCarousel si hay datos
                    if (recommendations.isNotEmpty()) {
                        RecommendationCarousel(
                            recommendationList = recommendations,
                            navController = navController,
                            viewModel = viewModel
                            )
                    } else {
                        Text(
                            "No recommendations available.",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
                // Botones de acciones
                ActionButtons()
            }
        }
}

@Composable
fun MoodSection(mood: Mood) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = mood.emojiResId),
            contentDescription = stringResource(id = mood.moodTextResId),
            modifier = Modifier.size(64.dp)
        )
        Text(text = stringResource(id = mood.moodTextResId), fontSize = 16.sp)
    }
}

@Composable
fun ActionButtons() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { /* Acción Agregar */ },
            modifier = Modifier
                .fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7B61FF),
            contentColor = Color.White
            ),
        ) {
            Text(stringResource(R.string.add_recommendation_to_my_list_esp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = { /* Acción No recomendar */ },
            modifier = Modifier.fillMaxWidth(0.8f)) {
            Text(stringResource(R.string.dont_recommend_again_esp))
        }
    }
}
