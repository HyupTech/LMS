package com.example.lms.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Rental(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,

        @ManyToOne
        val book: Book,

        val rentalDate: LocalDate,
        val returnDate: LocalDate?
)