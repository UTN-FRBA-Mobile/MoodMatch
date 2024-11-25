package utn.frba.mobile.moodmatch.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import coil.compose.AsyncImage
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Platform
import utn.frba.mobile.moodmatch.common.PurpleButton
import utn.frba.mobile.moodmatch.data.model.Activity
import utn.frba.mobile.moodmatch.data.model.Book
import utn.frba.mobile.moodmatch.data.model.Enterteinment
import utn.frba.mobile.moodmatch.data.model.Movie
import utn.frba.mobile.moodmatch.data.model.Series
import utn.frba.mobile.moodmatch.screens.viewmodel.MainViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InformationScreen(
    someThing : Enterteinment,
    navController: NavController,
    viewModel: MainViewModel
) {

    val context = LocalContext.current
    val recommendation = viewModel.selectedRecommendation

    Log.d("InformationScreen", " recommendation: $recommendation")

    Scaffold{
        val state = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state)
                .background(Backgroud()), // Cambia este color según tu fondo
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header con imagen de perfil, logo y ícono de menú
            Header()
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                model = someThing.image,
                contentDescription = "imagen descriptiva",
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
                /*contentScale = ContentScale.FillWidth,
                modifier = Modifier.height(200.dp)
                        .width(200.dp)
                //modifier = Modifier.fillMaxWidth()*/

            Spacer(modifier = Modifier.height(20.dp))
            GeneralInformation(someThing)
            Spacer(modifier = Modifier.height(20.dp))
            Sinopsis(someThing.sinopsis)
            when (someThing){
                is Movie -> Platform(someThing.plataforma)
                is Series -> Platform(someThing.plataforma)
            }
            Spacer(modifier = Modifier.height(16.dp))
            when (someThing){
                is Movie -> Score(someThing.score.toString())
                is Book -> Score(someThing.score.toString())
                is Series -> Score(someThing.score.toString())
            }

            Spacer(modifier = Modifier.height(20.dp))
            PurpleButton(text = stringResource(R.string.ver_ahora), onClick = { launchNetflix(context,"81630891") })
            Spacer(modifier = Modifier.height(15.dp))

        }
    }
}

fun launchNetflix(context:Context,id:String){
    val watchUrl = "https://www.netflix.com/title/$id"
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(watchUrl)
    )

    try {
        intent.setClassName(
            "com.netflix.mediaclient",
            "com.netflix.mediaclient.ui.launch.UIWebViewActivity"
        )
        context.startActivity(intent)
    } catch (e: Exception) {
        val playStoreIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=com.netflix.mediaclient")
        )
        context.startActivity(playStoreIntent)
    }
    context.startActivity(intent)
}

@Composable
fun Score(puntuacion:String){
    Row {
        Icon(
            painter = painterResource(
                R.drawable.ic_star_outline
            ),
            contentDescription = "Puntuacion",
            modifier = Modifier
                .size(20.dp),
            Color.Yellow
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = puntuacion + " - IMDb",
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )
    }
}

@Composable
fun Platform(plataforma:Platform){
    var painter = painterResource(id = R.drawable.prime_video)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(12.dp), // Padding interno del contenedor
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Disponible en:",
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(20.dp))
            when(plataforma) {
                Platform.NETFLIX -> painter = painterResource(id = R.drawable.netflix)
                Platform.PRIME -> painter = painterResource(id = R.drawable.prime_video)
                Platform.HBO -> painter = painterResource(id = R.drawable.hbo)
                else -> painter = painterResource(id = R.drawable.prime_video)
            }
            Image (
                painter = painter,
                contentDescription = "plataforma",
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

@Composable
fun GeneralInformation(elemento: Enterteinment){
    @Composable
    fun elems(nombre: String, valor:String){
        Text(
            buildAnnotatedString {

                withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                    withStyle(style = SpanStyle(color = Color.DarkGray)) {
                        append(nombre + ": ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color.DarkGray)) {
                        append(valor)
                    }
                }
            }
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(
                color = Color(0xFFE7ECFB),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(10.dp), // Padding interno del contenedor
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            elems(stringResource(R.string.titulo),elemento.name)
            when (elemento){
                is Movie -> elems(stringResource(R.string.director),elemento.director)
                is Book ->  elems(stringResource(R.string.autor),elemento.autor)
                is Series -> elems(stringResource(R.string.director),elemento.director)
            }
            if(elemento is Activity)
                elems(stringResource(R.string.clasificacion),elemento.classification)
            else
                elems(stringResource(R.string.genero),elemento.classification)



        }
    }

}

@Composable
fun Sinopsis(sinopsis:String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(
                color = Color(0xFFE7ECFB),
                shape = RoundedCornerShape(24.dp)
            ), // Padding interno del contenedor
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                style = LocalTextStyle.current.merge(
                    TextStyle(
                lineHeight = 1.5.em)),
                text = sinopsis,
                fontSize = 10.sp,
                textAlign = TextAlign.Start,
                color = Color.DarkGray
            )
        }
    }
}

/*@Preview
@Composable
fun InformationScreenPreview() {
    MoodMatchTheme {
        InformationScreen(Movie("Lord of War",Platform.PRIME,"Accion","Alguna","Ambientada en la Tercera Edad de La Tierra Media, mundo inventado por J.R.R. Tolkien. Narra una gran aventura: el viaje emprendido por 9 compañeros para destruir un Anillo lleno de poder maléfico. Su argumento es complejo y se narra con la participación de varios protagonistas que se mueven en varios hilos narrativos. Es una obra coral en la que destacan unos pocos protagonistas (Frodo, Sam, Gandalf y Aragorn).",9.72,"Tarantino"))
    }
}*/