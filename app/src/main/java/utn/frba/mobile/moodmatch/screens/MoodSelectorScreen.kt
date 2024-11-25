package utn.frba.mobile.moodmatch.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Header
import utn.frba.mobile.moodmatch.common.Mood
import utn.frba.mobile.moodmatch.common.PurpleButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoodSelectorScreen(navController: NavHostController) {
    val moods = listOf(
        Mood.ANGRY,
        Mood.SAD,
        Mood.NEUTRAL,
        Mood.VERYGOOD,
        Mood.INCREDIBLE
    )

    var selectedMoodIndex by remember { mutableStateOf(1) }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Backgroud()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            Spacer(modifier = Modifier.height(50.dp))
            HeaderSection()
            Spacer(modifier = Modifier.height(32.dp))

            // Carrusel de emociones
            MoodCarousel(
                moods = moods,
                selectedMoodIndex = selectedMoodIndex,
                onMoodSelected = { selectedMoodIndex = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de navegación
            PurpleButton(
                text = stringResource(R.string.select_esp),
                onClick = { navController.navigate("recomendacion/${moods[selectedMoodIndex]}") }
            )
        }
    }
}

@Composable
fun MoodCarousel(
    moods: List<Mood>,
    selectedMoodIndex: Int,
    onMoodSelected: (Int) -> Unit
) {
    val listState = rememberLazyListState()

    // Detectar el elemento centrado en el carrusel
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val center = listState.layoutInfo.viewportStartOffset + listState.layoutInfo.viewportEndOffset / 2
                val closestItem = visibleItems.minByOrNull {
                    kotlin.math.abs(it.offset + it.size / 2 - center)
                }
                closestItem?.let { onMoodSelected(it.index) }
            }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(horizontal = 64.dp)
        ) {
            itemsIndexed(moods) { index, mood ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(if (index == selectedMoodIndex) 80.dp else 64.dp)
                        .background(Color(0xFFFFF0E5), shape = CircleShape)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = mood.emojiResId),
                        contentDescription = stringResource(id = mood.moodTextResId),
                        modifier = Modifier.size(if (index == selectedMoodIndex) 60.dp else 40.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Texto con el nombre del Mood seleccionado
        Text(
            text = stringResource(id = moods[selectedMoodIndex].moodTextResId),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Indicadores de selección
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            moods.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(if (index == selectedMoodIndex) 8.dp else 6.dp)
                        .background(
                            if (index == selectedMoodIndex) Color.Gray else Color.LightGray,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        IconButton(onClick = { /* Acción de cerrar */ }) {
//            Icon(Icons.Default.Close, contentDescription = "Cerrar")
//        }
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.whats_your_mood_esp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.select_mood_esp),
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

//@Preview
//@Composable
//fun MoodSelectorPreview(){
//    MoodMatchTheme {
//        MoodSelectorScreen()
//    }
//}
