package com.yahya.filmticket.model

data class FilmTicket(
    val id: Long,
    val image: Int,
    val title: String,
    val requiredPrice: Int,
    val synopsis: String,
)