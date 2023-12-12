package com.yahya.filmticket.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yahya.filmticket.data.FilmTicketRepository
import com.yahya.filmticket.ui.screen.cart.CartViewModel
import com.yahya.filmticket.ui.screen.detail.DetailFilmTicketViewModel
import com.yahya.filmticket.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: FilmTicketRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailFilmTicketViewModel::class.java)) {
            return DetailFilmTicketViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}