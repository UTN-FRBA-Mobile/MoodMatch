package utn.frba.mobile.moodmatch

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import utn.frba.mobile.moodmatch.common.Mood
import utn.frba.mobile.moodmatch.common.Platform
import utn.frba.mobile.moodmatch.data.model.Movie
import utn.frba.mobile.moodmatch.navigator.moodMatchBottomRowScreens
import utn.frba.mobile.moodmatch.repository.UserRepositoryImp
import utn.frba.mobile.moodmatch.screens.CommunityScreen
import utn.frba.mobile.moodmatch.screens.HomeScreen
import utn.frba.mobile.moodmatch.screens.InformationScreen
import utn.frba.mobile.moodmatch.screens.InitialScreen
import utn.frba.mobile.moodmatch.screens.MoodSelectorScreen
import utn.frba.mobile.moodmatch.screens.RecommendationScreen
import utn.frba.mobile.moodmatch.screens.SignInScreen
import utn.frba.mobile.moodmatch.screens.viewmodel.SignInScreenViewModel
import utn.frba.mobile.moodmatch.screens.viewmodel.SignInViewModelFactory

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
                val userRepository = UserRepositoryImp()
                val viewModel: SignInScreenViewModel = viewModel(factory = SignInViewModelFactory(userRepository))

                SignInScreen(
                    viewModel = viewModel,
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

        }
            navigation(startDestination = "mood_selector", route = "mood"){
                composable("mood_selector") {
                    MoodSelectorScreen(navController)
                }
                composable(
                    route = "recomendacion/{emocion}",
                    arguments = listOf(
                        navArgument(name = "emocion"){
                            type = NavType.StringType
                        }
                    )
                    ) { backStackEntry ->
                    val emocionString = backStackEntry.arguments?.getString("emocion")
                    val emocion = try {
                        Mood.valueOf((emocionString ?: Mood.NEUTRAL).toString())
                    } catch (e: IllegalArgumentException) {
                        Mood.NEUTRAL
                    }
                    RecommendationScreen(emocion = emocion, navController =  navController)
                }
                composable(
                    route = "information/{tipo}",
                    arguments = listOf(
                        navArgument(name = "tipo"){
                            type = NavType.StringType
                        }
                    )
                ) { backStackEntry ->
                    val tipo = backStackEntry.arguments?.getString("tipo")

                    InformationScreen(
                        Movie("Lord of War",
                            Platform.PRIME,"Accion","Alguna","Ambientada en la Tercera Edad de La Tierra Media, mundo inventado por J.R.R. Tolkien. Narra una gran aventura: el viaje emprendido por 9 compañeros para destruir un Anillo lleno de poder maléfico. Su argumento es complejo y se narra con la participación de varios protagonistas que se mueven en varios hilos narrativos. Es una obra coral en la que destacan unos pocos protagonistas (Frodo, Sam, Gandalf y Aragorn).",9.72,"Tarantino")
                    )
                }
            }

            composable("comunidad") {
                CommunityScreen()
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