package utn.frba.mobile.moodmatch.navigator

import utn.frba.mobile.moodmatch.R

sealed interface MoodMatchDestination {
    val icon: Int
    val route: String
    val screenName: String
    val iconSize: Int
}

data object Home : MoodMatchDestination {
    override val icon = R.drawable.ic_home
    override val iconSize = 20
    override val route = "home"
    override val screenName = "Home"
}

data object MoodSelector : MoodMatchDestination {
    override val icon = R.drawable.ic_mood
    override val iconSize = 45
    override val route = "mood_selector"
    override val screenName = ""
}

data object Comunidad : MoodMatchDestination {
    override val icon = R.drawable.ic_community
    override val iconSize = 20
    override val route = "comunidad"
    override val screenName = "Comunidad"
}

// Screens to be displayed in the bottom tab navigator
val moodMatchBottomRowScreens = listOf(Home, MoodSelector, Comunidad)