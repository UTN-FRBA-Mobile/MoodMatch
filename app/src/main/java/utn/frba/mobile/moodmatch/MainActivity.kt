package utn.frba.mobile.moodmatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utn.frba.mobile.moodmatch.ui.theme.MoodMatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoodMatchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Backgroud()),
    )

    Titulo()

    Presentacion()

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter

    ) {
        Column {
            Registro()
            InicioSesion()

        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoodMatchTheme {
        Greeting("Android")
    }
}

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
fun Titulo() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(25.dp),
        contentAlignment = Alignment.Center)
    {
    Row(modifier = Modifier
                    .wrapContentWidth( align = Alignment.CenterHorizontally)
                    ,
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
fun Registro() {
    Column(modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(1f)){
        ButtonRegistro()
    }
}

@Composable
fun ButtonRegistro() {
    Button(modifier = Modifier
        .fillMaxWidth()
        ,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary)),
        onClick = { print("Hello") }) {
        Text(text = stringResource(id = R.string.register))
    }
}

@Composable
fun InicioSesion() {
    Row(modifier = Modifier.padding(40.dp)){
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