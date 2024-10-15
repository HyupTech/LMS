package com.example.lms.domain.repository

import com.example.lms.domain.entity.Rental
import org.springframework.data.jpa.repository.JpaRepository

interface RentalRepository : JpaRepository<Rental, Long>