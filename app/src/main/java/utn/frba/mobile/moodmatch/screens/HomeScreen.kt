package utn.frba.mobile.moodmatch.screens

import android.annotation.SuppressLint
import android.graphics.Color.rgb
import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import utn.frba.mobile.moodmatch.screens.viewmodel.MainViewModel
import java.util.Locale

// TODO datos mockeados que deberian llegar del servidor
var userName = "Cami"
var profilePicture = R.drawable.user_profile_picture

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    val recommendations by viewModel.recommendations.collectAsState()
    //val userData by viewmodel.login.collectAsState()
    val isLoading by viewModel.isLoading

    Scaffold(bottomBar = {}){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Backgroud())
                .padding(16.dp)
        ){
            Header()
            UserGreeting()
            Spacer(modifier = Modifier.height(10.dp))
            Titles()
            Spacer(modifier = Modifier.height(10.dp))
            RecommendationButtons(navController)
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                if (recommendations.isNotEmpty()) {
                    RecommendationCarousel(
                        recommendationList = recommendations,
                        navController = navController,
                        viewModel = viewModel
                    )
                } else {
                    Text(
                        stringResource(id = R.string.noMoodSelected),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun UserGreeting() {
    Box(
        modifier = Modifier.height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
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
                fontFamily = FontFamily(Font(R.font.poppins_normal)),
                style = TextStyle(fontSize = 18.sp),
            )
        }
    }
}

@Composable
fun Titles() {
    Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
        Column {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.home_greeting),
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                style = TextStyle(fontSize = 18.sp),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.elegi_recomendacion),
                fontFamily = FontFamily(Font(R.font.poppins_normal)),
                style = TextStyle(fontSize = 14.sp),
            )
        }
    }
}

@Composable
fun RecommendationButtons(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.peliculas),
                        contentDescription = "peliculas",
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clickable {
                                navController.navigate("contentList/movies")
                            },
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(R.drawable.libros),
                        contentDescription = "libros",
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clickable {
                                navController.navigate("contentList/books")
                            },
                        contentScale = ContentScale.Crop
                    )
                }
                Image(
                    painter = painterResource(R.drawable.meditaciones),
                    contentDescription = "meditaciones",
                    modifier = Modifier.size(359.dp, 95.dp)
                        .clickable {
                            navController.navigate("contentList/mediations")
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

@Composable
fun RecommendationCarousel(
    recommendationList: List<Recommendation> ,
    navController: NavController,
    viewModel: MainViewModel
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        Column {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.recomendaciones),
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                style = TextStyle(fontSize = 19.sp),
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow (horizontalArrangement = Arrangement.spacedBy(10.dp)){
                items(recommendationList) { recommendation ->
                    RecommendationCard(
                        recommendation = recommendation,
                        navController = navController,
                        viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun RecommendationCard(
    recommendation: Recommendation,
    navController: NavController,
    viewModel: MainViewModel
) {
    Column {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(BorderStroke(1.dp, Color.White))
                .background(Color(color = rgb(240, 248, 255)))
                .clickable {
                    viewModel.setRecommendation(recommendation)
                    val title = viewModel.selectedRecommendation?.title
                    navController.navigate("information/$title")
                }
                .padding(10.dp)
                .padding(bottom = 5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
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
                Text(
                    modifier = Modifier,
                    text = getTypeName(recommendation.type)
                        .uppercase(Locale.getDefault()),
                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                    style = TextStyle(fontSize = 10.sp),
                )
                Text(
                    modifier = Modifier,
                    text = recommendation.title,
                    fontFamily = FontFamily(Font(R.font.poppins_normal)),
                    style = TextStyle(fontSize = 10.sp),
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Rounded.Star,
                        contentDescription = "score",
                        Modifier.size(15.dp)
                    )
                    Text(
                        modifier = Modifier,
                        text = recommendation.score.toString(),
                        fontFamily = FontFamily(Font(R.font.poppins_normal)),
                        style = TextStyle(fontSize = 10.sp),
                    )
                }
            }
        }
    }
}

//todo usar el de commons pero pisando el padding
@Composable
fun Header() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        // todo arreglar el padding para home
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
