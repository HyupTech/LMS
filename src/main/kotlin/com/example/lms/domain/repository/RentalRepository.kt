package com.example.lms.domain.repository

import Rental

import org.springframework.data.jpa.repository.JpaRepository

interface RentalRepository : JpaRepository<Rental, Long>