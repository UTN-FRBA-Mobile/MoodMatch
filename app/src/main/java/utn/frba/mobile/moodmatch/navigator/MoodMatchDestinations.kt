package utn.frba.mobile.moodmatch.navigator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface MoodMatchDestination {
    val icon: ImageVector
    val route: String
    val screenName: String
}

data object Home : MoodMatchDestination {
    override val icon = Icons.Filled.Home
    override val route = "home"
    override val screenName = "Home"
}

data object MoodSelector : MoodMatchDestination {
    override val icon = Icons.Filled.Add
    override val route = "mood_selector"
    override val screenName = "Mood"
}

data object Comunidad : MoodMatchDestination {
    override val icon = Icons.Filled.Person
    override val route = "comunidad"
    override val screenName = "Comunidad"
}

// Screens to be displayed in the bottom tab navigator
val moodMatchBottomRowScreens = listOf(Home, MoodSelector, Comunidad)