package utn.frba.mobile.moodmatch.common

enum class Types {
    Series, Movies, Books, RecreationalAcitivies
}

fun getValueString(type: Types): String {
    return when (type) {
        Types.Series -> "series"
        Types.Movies -> "movies"
        Types.Books -> "books"
        Types.RecreationalAcitivies -> "recreational-activities"
    }
}