package com.example.lms.service

import Rental
import com.example.lms.domain.repository.RentalRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RentalService(private val rentalRepository: RentalRepository) {
    fun getAllRentals(): List<Rental> = rentalRepository.findAll()

    fun getRentalById(id: Long): Optional<Rental> {
        return rentalRepository.findById(id)
    }


    fun addRental(rental: Rental): Rental = rentalRepository.save(rental)

    fun updateRental(id: Long, updatedRental: Rental): Optional<Rental> {
        return rentalRepository.findById(id).map {
            it.book = updatedRental.book
            it.rentalDate = updatedRental.rentalDate
            it.returnDate = updatedRental.returnDate
            rentalRepository.save(it)
        }
    }

    fun deleteRental(id: Long): Boolean {
        return if (rentalRepository.existsById(id)) {
            rentalRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}