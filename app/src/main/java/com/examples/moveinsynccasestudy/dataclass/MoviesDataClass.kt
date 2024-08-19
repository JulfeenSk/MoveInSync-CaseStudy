package com.examples.moveinsynccasestudy.dataclass

import java.io.Serializable

data class MoviesDataClass(
    val actors: String,
    val director: String,
    val genres: List<String>,
    val id: Int,
    val plot: String,
    val posterUrl: String,
    val runtime: String,
    val title: String,
    val year: String

) : Serializable
