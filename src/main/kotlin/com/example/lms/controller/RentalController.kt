package com.example.lms.controller

import com.example.lms.domain.entity.Rental
import com.example.lms.service.RentalService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("rentals")
class RentalController(private val rentalService: RentalService) {

    @GetMapping
    fun getAllRentals(): List<Rental> = rentalService.getAllRentals()

    @GetMapping("/{id}")
    fun getRentalById(@PathVariable id: Long): ResponseEntity<Rental> {
        return rentalService.getRentalById(id).map { ResponseEntity.ok(it) }.orElse(ResponseEntity.notFound().build())
    }
}