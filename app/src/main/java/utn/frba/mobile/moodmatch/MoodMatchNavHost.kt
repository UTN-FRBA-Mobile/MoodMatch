package utn.frba.mobile.moodmatch

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import utn.frba.mobile.moodmatch.common.Recommendation
import utn.frba.mobile.moodmatch.data.model.Activity
import utn.frba.mobile.moodmatch.data.model.Book
import utn.frba.mobile.moodmatch.data.model.Movie
import utn.frba.mobile.moodmatch.navigator.moodMatchBottomRowScreens
import utn.frba.mobile.moodmatch.repository.UserRepositoryImp
import utn.frba.mobile.moodmatch.screens.CommunityScreen
import utn.frba.mobile.moodmatch.screens.ContentListScreen
import utn.frba.mobile.moodmatch.screens.HomeScreen
import utn.frba.mobile.moodmatch.screens.InformationScreen
import utn.frba.mobile.moodmatch.screens.InitialScreen
import utn.frba.mobile.moodmatch.screens.MoodSelectorScreen
import utn.frba.mobile.moodmatch.screens.RecommendationScreen
import utn.frba.mobile.moodmatch.screens.SignInScreen
import utn.frba.mobile.moodmatch.screens.viewmodel.MainViewModel
import utn.frba.mobile.moodmatch.screens.viewmodel.MainViewModelFactory
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

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppTabsNavGraph() {
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory())
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
                            if (screen.route == "home") {
                                // Caso específico para Home
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                    launchSingleTop = true
                                }
                            } else {
                                // Navegación genérica para las otras pantallas
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
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
                HomeScreen(
                    viewModel = viewModel,
                    navController = navController
                )
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
                    RecommendationScreen(
                        emocion = emocion,
                        viewModel = viewModel,
                        navController = navController)
                }
                composable(
                    route = "information/{tipo}",
                    arguments = listOf(
                        navArgument(name = "tipo"){
                            type = NavType.StringType
                        }
                    )
                ) {
                    val recommendation = viewModel.selectedRecommendation
                    if (recommendation != null) {
                        if (recommendation.type=="Movie"||recommendation.type=="Series"){
                            InformationScreen(
                                navController = navController,
                                viewModel = viewModel,
                                someThing =
                                Movie(recommendation.title,
                                    recommendation.platform,
                                    "Accion",//recommendation.genero
                                    recommendation.image,
                                    recommendation.sinopsis,
                                    Math.round(recommendation.score* 100.0)/100.toDouble(),
                                    recommendation.creator)
                            )
                        }else if (recommendation.type=="Book"){
                            InformationScreen(
                                navController = navController,
                                viewModel = viewModel,
                                someThing =
                                Book(recommendation.title,
                                    recommendation.creator,
                                    "Ciencia Ficcion",//recommendation.genero
                                    recommendation.image,
                                    recommendation.sinopsis,
                                    Math.round(recommendation.score * 100.0)/100.toDouble())
                            )
                        } else{
                            InformationScreen(
                                navController = navController,
                                viewModel = viewModel,
                                someThing =
                                Activity(recommendation.title,
                                    recommendation.creator,
                                    recommendation.image,
                                    recommendation.sinopsis)
                            )
                        }
                    }
                }
            }

            composable("comunidad") {
                CommunityScreen()
            }
            composable(
                route = "contentList/{tipo}",
                arguments = listOf(
                    navArgument(name = "tipo"){
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val recommendationList: List<Recommendation>
                val type = backStackEntry.arguments?.getString("tipo")

                if(type.equals("books")) {
                    LaunchedEffect(Unit) {
                        viewModel.getBooks()
                    }
                    val bookList by viewModel.books.collectAsState()
                    recommendationList = bookList.map { b ->
                        Recommendation(
                            title= b.name,
                            creator= b.autor,
                            image= b.image,
                            score= b.score.toFloat(),
                            type = "Book",
                            sinopsis = b.sinopsis,
                            platform = Platform.NA
                        )
                    }
                } else if(type.equals("movies")) {
                    LaunchedEffect(Unit) {
                        viewModel.getMovies()
                    }
                    val moviesList by viewModel.movies.collectAsState()
                    recommendationList = moviesList.map { b ->
                        Recommendation(
                            title= b.name,
                            creator= b.director,
                            image= b.image,
                            score= b.score.toFloat(),
                            type = "Movie",
                            sinopsis = b.sinopsis,
                            platform = b.plataforma ?: Platform.NA
                        )
                    }
                }
                else {
                    LaunchedEffect(Unit) {
                        viewModel.getRecreationalActivities()
                    }
                    val activitiesList by viewModel.recreationalActivities.collectAsState()
                    recommendationList = activitiesList.map { b ->
                        Recommendation(
                            title= b.name,
                            creator= "",
                            image= b.image,
                            score= 0f,
                            type = "Activity",
                            sinopsis = b.sinopsis,
                            platform = Platform.NA
                        )
                    }
                }
                if (type != null) {
                    ContentListScreen(
                        recommendationList = recommendationList,
                        navController = navController,
                        viewModel = viewModel
                    )
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