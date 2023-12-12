package com.yahya.filmticket.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yahya.filmticket.data.FilmTicketRepository
import com.yahya.filmticket.model.FilmTicket
import com.yahya.filmticket.model.OrderFilmTicket
import com.yahya.filmticket.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailFilmTicketViewModel(
    private val repository: FilmTicketRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderFilmTicket>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderFilmTicket>>
        get() = _uiState

    fun getFilmTicketById(filmTicketId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderFilmTicketById(filmTicketId))
        }
    }

    fun addToCart(filmTicket: FilmTicket, count: Int) {
        viewModelScope.launch {
            repository.updateOrderFilmTicket(filmTicket.id, count)
        }
    }
}