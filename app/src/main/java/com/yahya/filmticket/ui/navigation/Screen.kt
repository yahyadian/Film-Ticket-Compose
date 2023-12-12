package com.yahya.filmticket.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailFilmTicket : Screen("home/{filmTicketId}") {
        fun createRoute(filmTicketId: Long) = "home/$filmTicketId"
    }
}
