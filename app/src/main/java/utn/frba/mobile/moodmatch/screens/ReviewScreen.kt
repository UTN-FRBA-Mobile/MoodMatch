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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import utn.frba.mobile.moodmatch.R
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Header
import utn.frba.mobile.moodmatch.common.PurpleButton
import utn.frba.mobile.moodmatch.ui.theme.MoodMatchTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReviewScreen(movieTitle:String) {
    //TODO: Seguro necesite mas data (parametros) de la actividad o peli para guardar la review
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Backgroud()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            Spacer(modifier = Modifier.height(50.dp))
            HeaderSectionReview(movieTitle)

            Spacer(modifier = Modifier.height(24.dp))

            // Contenedor principal con fondo celeste y bordes redondeados
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(
                        color = Color(0xFFE7ECFB),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(24.dp), // Padding interno del contenedor
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StarRatingSection()
                    Spacer(modifier = Modifier.height(24.dp))
                    ReviewTextBox()
                    Spacer(modifier = Modifier.height(24.dp))
                    PurpleButton(text = stringResource(R.string.ready_esp), onClick = null)
                }
            }
        }
    }
}

@Composable
fun HeaderSectionReview(movieTitle: String) {
    // Movie title
    Text(
        text = stringResource(R.string.leave_your_opinion_esp),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = "\"$movieTitle\"",
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Gray
    )
}

@Composable
fun StarRatingSection() {
    // Estado que almacena la cantidad de estrellas seleccionadas
    var selectedStars by remember { mutableStateOf(0) }

    // Caja que contiene las estrellas, con bordes redondeados
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 45.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(5) { index ->
                Icon(
                    painter = painterResource(
                       R.drawable.ic_star_outline
                    ),
                    contentDescription = "Estrella ${index + 1}",
                    tint =
                        if (index < selectedStars)
                            Color(0XFF8c48ff)
                        else Color.LightGray
                    ,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { selectedStars = index + 1 },
                    // Al hacer clic, actualiza el estado
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}


@Composable
fun ReviewTextBox() {
    // Estado para almacenar el texto ingresado por el usuario
    var reviewText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        // Caja de texto editable
        OutlinedTextField(
            value = reviewText,
            onValueChange = { reviewText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = stringResource(R.string.leave_your_review_here_esp))
                          },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.LightGray,
                cursorColor = Color.Gray
            ),
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Preview
@Composable
fun ReviewScreenPreview() {
    MoodMatchTheme {
        ReviewScreen("Lord Of War")
    }
}
