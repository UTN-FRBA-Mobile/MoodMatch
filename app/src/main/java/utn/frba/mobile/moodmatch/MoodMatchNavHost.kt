package utn.frba.mobile.moodmatch

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import utn.frba.mobile.moodmatch.screens.InitialScreen
import utn.frba.mobile.moodmatch.screens.SignInScreen
import utn.frba.mobile.moodmatch.screens.MoodSelectorScreen
import utn.frba.mobile.moodmatch.screens.RecommendationScreen

@Composable
fun MoodMatchNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "auth",
        modifier = modifier
    ) {
        navigation(
            startDestination = "initial",
            route = "auth"
        ){
            composable("initial"){
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                InitialScreen(
                    name = "Android",
                    goNext = { navController.navigateSingleTopTo("sign_in") }
                )
            }
            composable("sign_in"){
                val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                SignInScreen(
                    goNext = { navController.navigateSingleTopTo("app") }
                )
            }
        }
        navigation(
            startDestination = "home",
            route = "app"
        ){
            composable("home") {
            }
            composable("mood_selector") {
                MoodSelectorScreen()
            }
            composable("recommendations") {
                RecommendationScreen()
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ){
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

//val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = "initial"){
//
//                }