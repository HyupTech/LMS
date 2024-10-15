package com.example.lms.service

import com.example.lms.domain.entity.Book
import com.example.lms.domain.repository.BookRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService(private val bookRepository: BookRepository) {
    fun getAllBooks(): List<Book> = bookRepository.findAll()

    fun getBookById(id: Long): Optional<Book> = bookRepository.findById(id)
}