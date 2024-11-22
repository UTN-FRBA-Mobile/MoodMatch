package utn.frba.mobile.moodmatch.data.model

import utn.frba.mobile.moodmatch.common.Platform

data class Series(
    override val name: String,
    val plataforma: Platform,
    override val classification: String,
    override val image: String,
    override val sinopsis: String,
    val score: Double,
    val director: String
):Enterteinment