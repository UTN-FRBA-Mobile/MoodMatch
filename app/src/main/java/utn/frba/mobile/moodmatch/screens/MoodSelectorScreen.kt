package utn.frba.mobile.moodmatch.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Header
import utn.frba.mobile.moodmatch.common.Mood
import utn.frba.mobile.moodmatch.common.PurpleButton
import utn.frba.mobile.moodmatch.ui.theme.MoodMatchTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoodSelectorScreen() {
    Scaffold{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Backgroud()), // Cambia este color según tu fondo
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            Spacer(modifier = Modifier.height(50.dp))
            HeaderSection()
            Spacer(modifier = Modifier.height(32.dp))
            MoodSelectorSection()
            Spacer(modifier = Modifier.height(32.dp))
            PurpleButton(stringResource(R.string.select_esp))
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

@Composable
fun MoodSelectorSection() {
    // Lista de moods
    val moods = listOf(
        Mood.ANGRY,
        Mood.SAD,
        Mood.NEUTRAL,
        Mood.VERYGOOD,
        Mood.INCREDIBLE
    )

    var selectedMoodIndex by remember { mutableStateOf(1) }
    val listState = rememberLazyListState()

    // Centrar el elemento seleccionado cuando cambia `selectedMoodIndex`
    LaunchedEffect(selectedMoodIndex) {
        launch {
            listState.animateScrollToItem(
                selectedMoodIndex,
                // Compensar para centrar el elemento seleccionado
                scrollOffset = -listState.layoutInfo.viewportEndOffset / 4
            )
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
                        .clickable {
                            selectedMoodIndex = index
                        }
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

        Text(
            text = stringResource(id = moods[selectedMoodIndex].moodTextResId),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))
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

@Preview
@Composable
fun MoodSelectorPreview(){
    MoodMatchTheme {
        MoodSelectorScreen()
    }
}
