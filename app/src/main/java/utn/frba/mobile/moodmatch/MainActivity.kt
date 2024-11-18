package utn.frba.mobile.moodmatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import utn.frba.mobile.moodmatch.screens.viewmodel.MainViewModel
import utn.frba.mobile.moodmatch.ui.theme.MoodMatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
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
    val navController = rememberNavController()
    MoodMatchNavHost(
        navController = navController,
        modifier = modifier
    )
    val viewModel = MainViewModel()
    viewModel.getRecommendations("series", "deportivo")
    viewModel.getRecommendations("movies", "deportivo")
    viewModel.getRecommendations("books", "deportivo")
    viewModel.getRecommendations("activity", "deportivo")
    //InitialScreen("Android")
    //SignInScreen()
    //RecommendationScreen()
    //MoodSelectorScreen()
//    ReviewScreen("Lord of War")
}

@Composable
inline fun <reified T:ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel ()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
