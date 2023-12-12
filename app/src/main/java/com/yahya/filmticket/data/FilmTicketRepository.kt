package com.yahya.filmticket.data

import com.yahya.filmticket.model.FakeFilmTicketDataSource
import com.yahya.filmticket.model.OrderFilmTicket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FilmTicketRepository {

    private val orderFilmTickets = mutableListOf<OrderFilmTicket>()

    init {
        if (orderFilmTickets.isEmpty()) {
            FakeFilmTicketDataSource.dummyFilmTickets.forEach {
                orderFilmTickets.add(OrderFilmTicket(it, 0))
            }
        }
    }

    fun getAllFilmTickets(): Flow<List<OrderFilmTicket>> {
        return flowOf(orderFilmTickets)
    }

    fun getOrderFilmTicketById(filmTicketId: Long): OrderFilmTicket {
        return orderFilmTickets.first {
            it.filmTicket.id == filmTicketId
        }
    }

    fun updateOrderFilmTicket(filmTicketId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderFilmTickets.indexOfFirst { it.filmTicket.id == filmTicketId }
        val result = if (index >= 0) {
            val orderFilmTicket = orderFilmTickets[index]
            orderFilmTickets[index] =
                orderFilmTicket.copy(filmTicket = orderFilmTicket.filmTicket, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderFilmTickets(): Flow<List<OrderFilmTicket>> {
        return getAllFilmTickets()
            .map { orderFilmTickets ->
                orderFilmTickets.filter { orderFilmTicket ->
                    orderFilmTicket.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: FilmTicketRepository? = null

        fun getInstance(): FilmTicketRepository =
            instance ?: synchronized(this) {
                FilmTicketRepository().apply {
                    instance = this
                }
            }
    }
}