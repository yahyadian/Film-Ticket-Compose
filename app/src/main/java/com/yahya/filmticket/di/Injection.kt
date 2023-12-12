package com.yahya.filmticket.di

import com.yahya.filmticket.data.FilmTicketRepository


object Injection {
    fun provideRepository(): FilmTicketRepository {
        return FilmTicketRepository.getInstance()
    }
}