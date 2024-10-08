package utn.frba.mobile.moodmatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import utn.frba.mobile.moodmatch.ui.theme.MoodMatchTheme
import utn.frba.mobile.moodmatch.screens.SignInScreen
import utn.frba.mobile.moodmatch.screens.InitialScreen
import utn.frba.mobile.moodmatch.screens.RecommendationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoodMatchTheme {
               MoodMatchApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoodMatchApp(modifier: Modifier = Modifier) {
//    InitialScreen("Android")
    //SignInScreen()
    RecommendationScreen()
}

