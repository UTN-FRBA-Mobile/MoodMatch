package utn.frba.mobile.moodmatch.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utn.frba.mobile.moodmatch.ui.common.Backgroud
import utn.frba.mobile.moodmatch.ui.common.Header
import utn.frba.mobile.moodmatch.R

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Backgroud())
        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
    ){
        Column {
            Header()
            Title()
            SignInContent()
        }
    }
}

@Composable
fun Title() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.bienvenido),
            fontFamily = FontFamily(
                Font(R.font.poppins_bold)
            ),
            style = TextStyle(
                fontSize = 24.sp,
            ),
        )
    }
}

@Composable
fun SignInContent() {
    Column {
        SocialLogin()

        Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
            Text(modifier = Modifier,
                text = stringResource(id = R.string.ingresar_con_mail)
                    .uppercase(),
                fontFamily = FontFamily(
                    Font(R.font.poppins_bold)
                ),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.gray)
                ),
            )
        }

        SiginForm()
        SigninButton()
        ForgotPassword()
    }
}

@Composable
fun SocialLogin() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 50.dp, bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        OutlinedButton(
            onClick = { print("Hello") },
            border = BorderStroke(0.25.dp, colorResource(id = R.color.off_white))
        )
        {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(18.dp)
            )
            Box(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.google_login)
                        .uppercase(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.secondary_one),
                        fontWeight = FontWeight(500)
                    ),

                    )
            }
        }
    }

}
@Composable
fun OutlinedButtonExample(onClick: () -> Unit) {

}

@Composable
fun SiginForm() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(modifier = Modifier
        .padding(top = 40.dp, bottom = 20.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ){
//        TextField(
//            value = email,
//            onValueChange = { email = it },
//            placeholder = { Text(stringResource(id = R.string.email)) },
//            shape = RoundedCornerShape(15.dp),
//            singleLine = true,
//            colors = TextFieldDefaults.colors(
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                disabledIndicatorColor = Color.Transparent
//            ),
//            contentPadding = TextFieldDefaults.contentPaddingWithLabel(
//                top = 0.dp
//            )
//        )
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(stringResource(id = R.string.contrasena)) },
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SigninButton() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 50.dp, bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        OutlinedButton(
            onClick = { print("Hello") },
            border = BorderStroke(2.dp, colorResource(id = R.color.primary)),
            modifier = Modifier.padding(start = 40.dp, end = 40.dp)
        )
        {

            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.ingresa)
                    .uppercase(),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.primary),
                    fontWeight = FontWeight(800)
                ),
            )
        }
    }
}

@Composable
fun ForgotPassword() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.olvidar_contrasena),
            fontFamily = FontFamily(
                Font(R.font.poppins_normal)
            ),
            style = TextStyle(
                fontSize = 14.sp,
                color = colorResource(id = R.color.off_black),
                fontWeight = FontWeight(600)

            ),
        )
    }
}