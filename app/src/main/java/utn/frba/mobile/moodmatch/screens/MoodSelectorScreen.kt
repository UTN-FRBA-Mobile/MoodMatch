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
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Header
import utn.frba.mobile.moodmatch.common.Mood
import utn.frba.mobile.moodmatch.common.PurpleButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoodSelectorScreen(navController: NavHostController) {
    // Agregar moods invisibles para manejar los extremos
    val moods = listOf(
        Mood.InvisibleStart,
        Mood.ANGRY,
        Mood.SAD,
        Mood.NEUTRAL,
        Mood.VERYGOOD,
        Mood.INCREDIBLE,
        Mood.InvisibleEnd
    )

    var selectedMoodIndex by remember { mutableStateOf(3) } // Inicialmente centrado en "Neutral"
    val listState = rememberLazyListState()

    // Desplazamiento inicial para centrar "Neutral"
    LaunchedEffect(Unit) {
        listState.scrollToItem(2) // Índice del mood "Neutral"
    }

    // Detectar el elemento más cercano al centro después de scrollear
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val viewportStart = listState.layoutInfo.viewportStartOffset
            val viewportEnd = listState.layoutInfo.viewportEndOffset
            val center = (viewportEnd - viewportStart) / 2 + viewportStart

            val closestItem = listState.layoutInfo.visibleItemsInfo.minByOrNull {
                kotlin.math.abs((it.offset + it.size / 2) - center)
            }

            closestItem?.let {
                val adjustedIndex = it.index.coerceIn(1, moods.size - 2) // Ignorar moods invisibles
                if (adjustedIndex != selectedMoodIndex) {
                    selectedMoodIndex = adjustedIndex
                    launch {
                        listState.animateScrollToItem(it.index)
                    }
                }
            }
        }
    }

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
                onMoodSelected = { index ->
                    selectedMoodIndex = index.coerceIn(1, moods.size - 2) // Evitar seleccionar invisibles
                },
                listState = listState
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
    onMoodSelected: (Int) -> Unit,
    listState: LazyListState
) {
    val itemSize = 80.dp
    val itemSpacing = 32.dp
    val paddingHorizontal = (itemSize + itemSpacing) / 15F

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(itemSpacing),
        contentPadding = PaddingValues(horizontal = paddingHorizontal)
    ) {
        itemsIndexed(moods) { index, mood ->
            val isSelected = index == selectedMoodIndex

            if (mood.name == "InvisibleStart" || mood.name == "InvisibleEnd") {
                // Ítems invisibles para los extremos
                Spacer(modifier = Modifier.size(itemSize))
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(if (isSelected) 100.dp else 80.dp)
                        .background(
                            color = if (isSelected) Color(0xFFFFE0B2) else Color(0xFFFFF0E5),
                            shape = CircleShape
                        )
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = mood.emojiResId),
                        contentDescription = stringResource(id = mood.moodTextResId),
                        modifier = Modifier.size(if (isSelected) 60.dp else 48.dp)
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Texto y puntos indicadores
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = moods[selectedMoodIndex].moodTextResId),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            moods.subList(1, moods.size - 1).forEachIndexed { index, _ -> // Ignorar moods invisibles
                Box(
                    modifier = Modifier
                        .size(if (index + 1 == selectedMoodIndex) 10.dp else 6.dp)
                        .background(
                            if (index + 1 == selectedMoodIndex) Color.Gray else Color.LightGray,
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
