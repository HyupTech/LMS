package com.example.lms.controller

import Book
import Rental
import com.example.lms.service.BookService
import com.example.lms.service.RentalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("rentals")
class RentalController(
        private val rentalService: RentalService,
        private val bookService: BookService // BookService 주입
) {

    @GetMapping
    fun getAllRentals(): List<Rental> = rentalService.getAllRentals()

    @GetMapping("/{id}")
    fun getRentalById(@PathVariable id: Long): ResponseEntity<Rental> {
        return rentalService.getRentalById(id).map { ResponseEntity.ok(it) }.orElse(ResponseEntity.notFound().build())
    }

    // 대여 기록 추가
    @PostMapping
    fun addRental(
            @RequestParam bookId: Long,
            @RequestParam rentalDate: String,
            @RequestParam(required = false) returnDate: String?
    ): ResponseEntity<Rental> {
        // Book 정보를 조회
        val book = bookService.getBookById(bookId)
                .orElseThrow { IllegalArgumentException("Book not found") } // Book이 없을 경우 예외 처리

        val rental = Rental(
                book = book,  // 실제 Book 객체 사용
                rentalDate = LocalDate.parse(rentalDate),
                returnDate = returnDate?.let { LocalDate.parse(it) }
        )

        val createdRental = rentalService.addRental(rental)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRental)
    }


    // 대여 기록 수정
    @PutMapping("/{id}")
    fun updateRental(
            @PathVariable id: Long,
            @RequestParam bookId: Long,
            @RequestParam rentalDate: String,
            @RequestParam(required = false) returnDate: String?
    ): ResponseEntity<Rental> {
        val updatedRental = Rental(
                book = Book(bookId),  // ID만으로 Book 객체 생성
                rentalDate = LocalDate.parse(rentalDate),
                returnDate = returnDate?.let { LocalDate.parse(it) }
        )
        return rentalService.updateRental(id, updatedRental).map { ResponseEntity.ok(it) }
                .orElse(ResponseEntity.notFound().build())
    }

    // 대여 기록 삭제
    @DeleteMapping("/{id}")
    fun deleteRental(@PathVariable id: Long): ResponseEntity<Void> {
        return if (rentalService.deleteRental(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}