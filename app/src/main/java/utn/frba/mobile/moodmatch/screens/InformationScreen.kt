package utn.frba.mobile.moodmatch.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Mood
import utn.frba.mobile.moodmatch.common.PurpleButton
import utn.frba.mobile.moodmatch.ui.theme.MoodMatchTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InformationScreen(emocion: String) {
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
            Image(
                painter = painterResource(id = R.drawable.lord_of_war),
                contentDescription = "Lord of War",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.height(200.dp)
                        .width(200.dp)
                //modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            GeneralInformation("Lord of War","Accion","Tarantino")
            Spacer(modifier = Modifier.height(20.dp))
            Sinopsis("Ambientada en la Tercera Edad de La Tierra Media, mundo inventado por J.R.R. Tolkien. Narra una gran aventura: el viaje emprendido por 9 compañeros para destruir un Anillo lleno de poder maléfico. Su argumento es complejo y se narra con la participación de varios protagonistas que se mueven en varios hilos narrativos. Es una obra coral en la que destacan unos pocos protagonistas (Frodo, Sam, Gandalf y Aragorn).")
            Platform(List(2,{i->"Netflix"}))
            Score("7.2")
            Spacer(modifier = Modifier.height(20.dp))
            PurpleButton(text = "Ver ahora", onClick = { ->"10" })
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
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
fun Platform(plataformas:List<String>){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(24.dp), // Padding interno del contenedor
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
            Row {
                Image(
                    painter = painterResource(id = R.drawable.netflix),
                    contentDescription = "Netflix",
                    modifier = Modifier.size(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.prime_video),
                    contentDescription = "Netflix",
                    modifier = Modifier.size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                )
            }

        }
    }
}

@Composable
fun GeneralInformation(nombre:String, clasificacion:String,director:String){
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

            elems("Titulo",nombre)
            elems("Director",director)
            elems("Genero",clasificacion)


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
            )
            .padding(12.dp), // Padding interno del contenedor
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = sinopsis,
                fontSize = 10.sp,
                textAlign = TextAlign.Start,
                color = Color.DarkGray
            )
        }
    }
}

@Preview
@Composable
fun InformationScreenPreview() {
    MoodMatchTheme {
        InformationScreen("Lord Of War")
    }
}