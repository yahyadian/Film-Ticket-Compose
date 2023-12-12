package com.yahya.filmticket.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yahya.filmticket.data.FilmTicketRepository
import com.yahya.filmticket.model.OrderFilmTicket
import com.yahya.filmticket.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: FilmTicketRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderFilmTicket>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderFilmTicket>>>
        get() = _uiState

    fun getAllFilmTickets() {
        viewModelScope.launch {
            repository.getAllFilmTickets()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}