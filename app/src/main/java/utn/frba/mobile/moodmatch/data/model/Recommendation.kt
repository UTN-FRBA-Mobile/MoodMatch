package utn.frba.mobile.moodmatch.data.model

data class Recommendation<T>(
    val result: Map<String, List<T>>
)
