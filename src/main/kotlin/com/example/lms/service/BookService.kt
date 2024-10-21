package com.example.lms.service

import Book
import com.example.lms.domain.repository.BookRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookService(private val bookRepository: BookRepository) {
    fun getAllBooks(): List<Book> = bookRepository.findAll()

    fun getBookById(id: Long): Optional<Book> = bookRepository.findById(id)

    fun addBook(book: Book): Book = bookRepository.save(book)

    fun updateBook(id: Long, updateBook: Book): Optional<Book> {
        return bookRepository.findById(id).map {
            it.title = updateBook.title
            it.author = updateBook.author
            bookRepository.save(it)
        }
    }

    fun deleteBook(id: Long): Boolean {
        return if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}