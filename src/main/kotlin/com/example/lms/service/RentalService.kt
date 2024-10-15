package com.example.lms.service

import com.example.lms.domain.entity.Rental
import com.example.lms.domain.repository.RentalRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RentalService(private val rentalRepository: RentalRepository) {
    fun getAllRentals(): List<Rental> = rentalRepository.findAll()

    fun getRentalById(id: Long): Optional<Rental> = rentalRepository.findById(id)
}