package utn.frba.mobile.moodmatch.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.BottomNavigationBar
import utn.frba.mobile.moodmatch.common.Header

// TODO datos mockeados que deberian llegar del servidor
var userName = "Cami"
var profilePicture = R.drawable.user_profile_picture;

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        },  ){
        Column (modifier = Modifier.background(Backgroud()).padding(16.dp),){
            Header()
            HomeContent()
        }
    }
}

@Composable
fun HomeContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        UserGreeting()
        Spacer(modifier = Modifier.height(10.dp))
        Titles()
        RecommendationButtons()
        RecomendationSelector()
    }
}

@Composable
fun UserGreeting() {
    Box(modifier = Modifier.height(50.dp),contentAlignment = Alignment.Center) {
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
            Text(modifier = Modifier.padding(top = 0.dp, bottom = 0.dp),
                text = "¡" + stringResource(id = R.string.hola) + ", " + userName + "!"
                    .uppercase(),
                fontFamily = FontFamily(
                    Font(R.font.poppins_normal)
                ),

                style = TextStyle(
                    fontSize = 18.sp

                ),
            )
        }

    }
}

@Composable
fun Titles() {
    Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
        Column() {
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
            Spacer(modifier = Modifier.height(10.dp))
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
                            .weight(1f)
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(R.drawable.libros),
                        contentDescription = "libros",
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                }
                Image(
                    painter = painterResource(R.drawable.meditaciones),
                    contentDescription = "meditaciones",
                    //modifier = Modifier
                    //    .size(360.dp,90.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

@Composable
fun RecomendationSelector() {
    Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxWidth()){
        Column() {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.recomendaciones),
                fontFamily = FontFamily(
                    Font(R.font.poppins_bold)
                ),

                style = TextStyle(
                    fontSize = 18.sp,
                    background = Color.Red
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))


            // tarjeta de carrousel
            Box(modifier = Modifier.background(Color.Red).padding(10.dp).padding(bottom = 10.dp)
                .clickable {
                    //selectedMoodIndex = index
                }) {
                Column  (verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Image(
                        painter = painterResource(R.drawable.libros),
                        contentDescription = "libros",
                        modifier = Modifier

                            .height(110.dp)
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )

                Text(
                    modifier = Modifier,
                    text = "PELICULA RANDOM",
                    fontFamily = FontFamily(
                        Font(R.font.poppins_bold)
                    ),
                    style = TextStyle(
                        fontSize = 10.sp,
                    ),
                )
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.todoBorrar2),
                        fontFamily = FontFamily(
                            Font(R.font.poppins_normal)
                        ),
                        style = TextStyle(
                            fontSize = 10.sp,
                        ),
                    )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Rounded.Star,
                        contentDescription = "7,5",
                        Modifier.size(15.dp)
                    )
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.todoBorrar),
                        fontFamily = FontFamily(
                            Font(R.font.poppins_normal)
                        ),
                        style = TextStyle(
                            fontSize = 10.sp,
                        ),
                    )

                }
                }
            }
        }
    }
}


