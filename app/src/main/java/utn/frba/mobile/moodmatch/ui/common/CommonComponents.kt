package utn.frba.mobile.moodmatch.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utn.frba.mobile.moodmatch.R


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