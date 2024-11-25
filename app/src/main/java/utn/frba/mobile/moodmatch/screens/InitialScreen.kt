package utn.frba.mobile.moodmatch.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import utn.frba.mobile.moodmatch.common.Header

@Composable
fun InitialScreen(
    name: String,
    goNext:  () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Backgroud()),
    )

    Header()

    Presentacion()

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter

    ) {
        Column {
            Registro(goNext)
            InicioSesion()

        }

    }

}

@Composable
fun Presentacion() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_mm),
                contentDescription = "Certified",
                modifier = Modifier
                    .padding(30.dp)
            )
            Row {
                Text(
                    text = stringResource(id = R.string.mood),
                    fontFamily = FontFamily(
                        Font(R.font.poppins_bold)
                    ),
                    style = TextStyle(
                        fontSize = 20.sp,
                    ),
                )
                Text(
                    text = stringResource(id = R.string.match),
                    fontFamily = FontFamily(
                        Font(R.font.poppins_normal)
                    ),
                    style = TextStyle(
                        fontSize = 20.sp,
                    ),
                )
            }
            Text(
                text = stringResource(id = R.string.leyenda),
                fontFamily = FontFamily(
                    Font(R.font.poppins_bold)
                ),
                style = TextStyle(
                    fontSize = 20.sp,
                ),
                modifier = Modifier
                    .padding(30.dp)
            )
        }

    }
}

@Composable
fun Registro(goNext: () -> Unit) {
    Column(modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(1f)){
        ButtonRegistro(goNext)
    }
}

@Composable
fun ButtonRegistro(
    goNext:  () -> Unit = {}
) {
    Button(modifier = Modifier
        .fillMaxWidth()
        ,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary)),
        onClick = { goNext() }) {
        Text(text = stringResource(id = R.string.ingresa))
    }
}

@Composable
fun InicioSesion(
) {
    Row(modifier = Modifier
        .padding(40.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(id = R.string.ya_registrado).uppercase(),
            fontFamily = FontFamily(
                Font(R.font.poppins_normal)
            ),
            style = TextStyle(
                fontSize = 20.sp,
            ),
            modifier = Modifier.padding(2.dp)

        )
        Text(
            text = stringResource(id = R.string.ingresa).uppercase(),
            fontFamily = FontFamily(
                Font(R.font.poppins_bold)
            ),
            style = TextStyle(
                fontSize = 20.sp,
                color = colorResource(id = R.color.primary)
            ),
            modifier = Modifier.padding(2.dp)
        )
    }
}
