package utn.frba.mobile.moodmatch.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utn.frba.mobile.moodmatch.R

//Esto va aca?
enum class Mood(val emojiResId: Int, val moodTextResId: Int) {
    NEUTRAL(R.drawable.ic_emoji_neutral, R.string.neutral_esp),
    VERYGOOD(R.drawable.ic_emoji_verygood, R.string.verygood_esp),
    SAD(R.drawable.ic_emoji_sad, R.string.sad_esp),
    INCREDIBLE(R.drawable.ic_emoji_incredible, R.string.incredible_esp),
    ANGRY(R.drawable.ic_emoji_angry, R.string.angry_esp)
}

data class Recommendation(val title: String, val subtitle: String, val image: Int, val score:Float)

@Composable
fun Backgroud(): Brush {
    val fondoDegradado = Brush.linearGradient(
        0.0f to colorResource(id = R.color.gradient_transition_one),
        0.5f to colorResource(id = R.color.gradient_transition_two),
        1.0f to colorResource(id = R.color.gradient_transition_three),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    return fondoDegradado
}

@Composable
fun Header() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(25.dp),
        contentAlignment = Alignment.Center)
    {
        Row(modifier = Modifier
            .wrapContentWidth( align = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(3.dp)
                    .alignByBaseline(),
                text = stringResource(id = R.string.mood),
                fontFamily = FontFamily(
                    Font(R.font.poppins_bold)
                ),
                style = TextStyle(
                    fontSize = 14.sp,
                ),

                )
            Image(
                painter = painterResource(id = R.drawable.logo_mm),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        colorResource(id = R.color.fondo_logo),
                        shape = RoundedCornerShape(10.dp)
                    ),
            )
            Text( modifier = Modifier
                .padding(3.dp)
                .alignByBaseline(),
                text = stringResource(id = R.string.match),
                fontFamily = FontFamily(
                    Font(R.font.poppins_normal)
                ),
                style = TextStyle(
                    fontSize = 14.sp,
                ),
            )

        }

    }
}

@Composable
fun RecommendationCarousel(recommendationList: List<Recommendation> ) {
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
            .height(210.dp)
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
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_star_outline),
                    contentDescription = null)
                Text(
                    text = recommendation.score.toString(),
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
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

//TODO: pasar funcion como parametro? para el onClick
@Composable
fun PurpleButton(text: String) {
    Button(
        onClick = { /* Acción de selección */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF7B61FF),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}