package com.example.lms.controller

import com.example.lms.domain.entity.Book
import com.example.lms.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {

    @GetMapping
    fun getAllBooks(): List<Book> = bookService.getAllBooks()

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Long): ResponseEntity<Book> {
        return bookService.getBookById(id).map { ResponseEntity.ok(it) }.orElse(ResponseEntity.notFound().build())
    }

}