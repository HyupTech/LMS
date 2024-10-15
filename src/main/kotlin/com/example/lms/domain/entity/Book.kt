package com.example.lms.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Book(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        val title: String,
        val author: String,
        val isRented: Boolean = false
)