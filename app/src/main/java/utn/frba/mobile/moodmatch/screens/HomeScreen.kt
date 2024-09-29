package utn.frba.mobile.moodmatch.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Footer
import utn.frba.mobile.moodmatch.common.Header

// TODO datos mockeados que deberian llegar del servidor
var userName = "Cami"
var profilePicture = R.drawable.user_profile_picture;

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Backgroud())
        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
    ){
        Column {
            Header()
            HomeContent()
            Footer()

        }
    }
}

@Composable
fun HomeContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        UserGreeting()
        //Titles()
        RecommendationButtons()
    }
}

@Composable
fun UserGreeting() {
    Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = profilePicture),
                contentDescription = "ProfilePicture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(modifier = Modifier.padding(top = 28.dp, bottom = 28.dp),
                text = "¡" + stringResource(id = R.string.hola) + ", " + userName + "!"
                    .uppercase(),
                fontFamily = FontFamily(
                    Font(R.font.poppins_normal)
                ),

                style = TextStyle(
                    fontSize = 14.sp

                ),
            )
        }

    }
}

@Composable
fun Titles() {
    Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.home_greeting),
                fontFamily = FontFamily(
                    Font(R.font.poppins_bold)
                ),
                style = TextStyle(
                    fontSize = 18.sp,
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.elegi_recomendacion),
                fontFamily = FontFamily(
                    Font(R.font.poppins_normal)
                ),
                style = TextStyle(
                    fontSize = 13.sp,
                ),
            )
        }

    }
}

@Composable
fun RecommendationButtons() {
    Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
            Column (verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Image(
                        painter = painterResource(R.drawable.peliculas),
                        contentDescription = "peliculas",
                        modifier = Modifier
                            .size(170.dp)
                            .background(Color.Red)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        painter = painterResource(R.drawable.libros),
                        contentDescription = "libros",
                        modifier = Modifier
                            .size(170.dp)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Image(
                    painter = painterResource(R.drawable.meditaciones),
                    contentDescription = "meditaciones",
                    modifier = Modifier
                        .size(340.dp)
                        .aspectRatio(21f / 9f)
                        .background(Color.Red)
                )
            }
        }
    }

