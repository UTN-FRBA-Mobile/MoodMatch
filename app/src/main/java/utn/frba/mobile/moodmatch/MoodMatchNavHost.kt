package utn.frba.mobile.moodmatch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import utn.frba.mobile.moodmatch.navigator.moodMatchBottomRowScreens
import utn.frba.mobile.moodmatch.screens.HomeScreen
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
        composable(route = "app") {
            AppTabsNavGraph()
        }
    }
}

@Composable
fun AppTabsNavGraph() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent,
                contentColor = Color.Gray
            ) {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination
                moodMatchBottomRowScreens.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        label = { Text(screen.screenName) },
                        icon = {
                            Icon(
                                painterResource(
                                    id = screen.icon
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(screen.iconSize.dp),
                                tint = Color.Unspecified
                            )
                        },
                        onClick = {
                            navController.navigate(screen.route) {
                                // findStartDestination -> Finds the actual start destination of the graph,
                                // handling cases where the graph's starting destination is itself a NavGraph(nested navigation)

                                // popUpTo :-  clears the back stack and the state of all
                                // destinations between the current destination and the NavOptionsBuilder.popUpTo ID
                                // But if we use saveState = true, it will save that state( back stack and the state of all
                                // destinations between the current destination and the NavOptionsBuilder.popUpTo ID)
                                // before it clears backstack entries upto popUpTo ID,
                                // and later it restore that backstack if we use restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                launchSingleTop = true

                                restoreState = true

                            }
                        },
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "app",
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(startDestination = "home", route = "app") {
            composable("home") {
                HomeScreen()
            }
            composable("mood_selector") {
                MoodSelectorScreen()
            }
            composable("comunidad") {
                RecommendationScreen()
            }
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