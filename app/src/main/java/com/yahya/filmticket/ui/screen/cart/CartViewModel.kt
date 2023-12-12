package com.yahya.filmticket.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yahya.filmticket.data.FilmTicketRepository
import com.yahya.filmticket.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: FilmTicketRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderRewards() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderFilmTickets()
                .collect { orderFilmTicket ->
                    val totalRequiredPrice =
                        orderFilmTicket.sumOf { it.filmTicket.requiredPrice * it.count }
                    _uiState.value = UiState.Success(CartState(orderFilmTicket, totalRequiredPrice))
                }
        }
    }

    fun updateOrderReward(filmTicketId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderFilmTicket(filmTicketId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderRewards()
                    }
                }
        }
    }

}