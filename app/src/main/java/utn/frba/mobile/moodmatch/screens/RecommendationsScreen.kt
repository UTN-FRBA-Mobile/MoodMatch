package utn.frba.mobile.moodmatch.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Header
import utn.frba.mobile.moodmatch.ui.theme.MoodMatchTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecommendationScreen() {
    Scaffold(
        bottomBar = {
            BottomNavigationBar() // Llamamos a la función que contiene nuestra barra de navegación
        } ){
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
                    modifier = Modifier.padding(16.dp)
                )

                // Emoji y estado de ánimo
                MoodSection()

                // Recomendaciones
                RecommendationCarousel()

                // Botones de acciones
                ActionButtons()
            }
        }
}

@Composable
fun MoodSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_neutral_emoji),
            contentDescription = stringResource(R.string.emotion_emoji_esp),
            modifier = Modifier.size(64.dp)
        )
        Text(text = "Neutral", fontSize = 16.sp)
    }
}

@Composable
fun RecommendationCarousel() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre tarjetas
    ) {
        items(recommendationList) { recommendation ->
            RecommendationCard(recommendation)
        }
    }
}

@Composable
fun RecommendationCard(recommendation: Recommendation) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .clickable { /* Acción al hacer clic en la tarjeta */ },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Color de fondo de la tarjeta
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp // Elevación (sombra) de la tarjeta
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = recommendation.image),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recommendation.title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = recommendation.subtitle,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }
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
        Button(onClick = { /* Acción Agregar */ }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text(stringResource(R.string.add_recommendation_to_my_list_esp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(onClick = { /* Acción No recomendar */ }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text(stringResource(R.string.dont_recommend_again_esp))
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.Transparent,
        contentColor = Color.Gray
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(
                        id = R.drawable.ic_home
                    ),
                    contentDescription = stringResource(R.string.home),
                    modifier = Modifier.size(20.dp)
                )
            },
            selected = true,
            onClick = { /* Acción Home */ },
            label = { Text(text = stringResource(id = R.string.home)) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_mood),
                    contentDescription = stringResource(id = R.string.mood),
                    modifier = Modifier.size(45.dp),
                    tint = Color.Unspecified
                )
            },
            selected = false,
            onClick = { /* Acción Mood */ },
            label = { Text(text = stringResource(id = R.string.mood)) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_community),
                    contentDescription = stringResource(R.string.comunity_esp),
                    modifier = Modifier.size(20.dp)
                )
            },
            selected = false,
            onClick = { /* Acción Comunidad */ },
            label = { Text(text = stringResource(id = R.string.comunity_esp)) }
        )
    }
}


@Preview
@Composable
fun RecommendationScreenPreview(){
    MoodMatchTheme {
        RecommendationScreen()
    }
}

// Datos de ejemplo
val recommendationList = listOf(
    Recommendation("PELÍCULA", "El señor de la guerra", R.drawable.lord_of_war),
    Recommendation("SERIE", "Westworld", R.drawable.westworld),
    // Añade más recomendaciones aquí
)

data class Recommendation(val title: String, val subtitle: String, val image: Int)