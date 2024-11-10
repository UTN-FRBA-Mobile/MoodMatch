package utn.frba.mobile.moodmatch.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utn.frba.mobile.moodmatch.MainActivity
import utn.frba.mobile.moodmatch.common.Backgroud
import utn.frba.mobile.moodmatch.common.Header
import utn.frba.mobile.moodmatch.R

@Preview(showBackground = true)
@Composable
fun MoodMatchApp(modifier: Modifier = Modifier) {
    SignInScreen()
}

@Composable
fun SignInScreen(goNext:  () -> Unit = {}, modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Backgroud())
        .padding(start = 16.dp, end = 16.dp, bottom = 10.dp)
    ){
        Column {
            Header()
            Title()
            SignInContent(goNext)
        }
    }
}

@Composable
fun Title() {
    Box(modifier = Modifier
        .fillMaxWidth(),
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
fun SignInContent(goNext: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SocialLogin()

        Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center) {
            Text(modifier = Modifier.padding(top = 28.dp, bottom = 28.dp),
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
        SigninButton(goNext)
        ForgotPassword()
    }
}

@Composable
fun SocialLogin() {
    Box(modifier = Modifier
        .fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = { print("Hello") },
            border = BorderStroke(0.5.dp, colorResource(id = R.color.off_white)),
            modifier = Modifier.fillMaxWidth()
        )
        {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(18.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.google_login)
                        .uppercase(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.secondary_one),
                        fontWeight = FontWeight(500)
                    ),)
        }
    }

}

data class Credentials(
    var login: String = "",
    var pwd: String = "",
    var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return login.isNotEmpty() && pwd.isNotEmpty()
    }
}

fun checkCredentials(creds: Credentials, context: Context): Boolean {
    if (creds.isNotEmpty() && creds.login == "admin") {
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as Activity).finish()
        return true
    } else {
        Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
        return false
    }
}

@Composable
fun SiginForm() {
    var credentials by remember { mutableStateOf(Credentials()) }

    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ){
        LoginField(
            value = credentials.login,
            onChange = { data -> credentials = credentials.copy(login = data) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(id = R.string.email)
        )
        LoginField(
            value = credentials.pwd,
            onChange = {  data -> credentials = credentials.copy(pwd = data) },
            placeholder = stringResource(id = R.string.contrasena)
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun LoginField(value: String,
               onChange: (String) -> Unit,
               modifier: Modifier = Modifier,
               placeholder: String = "Enter your Login",
               ) {

    TextField(
        value = value,
        onValueChange = onChange,
        placeholder = { Text(placeholder) },
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier.fillMaxWidth()
    )

}

@Composable
fun SigninButton(goNext: () -> Unit = {}) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = 20.dp)
    ) {
        OutlinedButton(
            onClick = { goNext() },
            border = BorderStroke(2.dp, colorResource(id = R.color.primary)),
            modifier = Modifier.fillMaxWidth()
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