package com.yahya.filmticket.ui.screen.cart

import com.yahya.filmticket.model.OrderFilmTicket

data class CartState(
    val orderFilmTicket: List<OrderFilmTicket>,
    val totalRequiredPrice: Int
)