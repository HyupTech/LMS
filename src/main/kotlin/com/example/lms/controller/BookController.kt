package com.example.lms.controller

import Book
import com.example.lms.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Long): ResponseEntity<Book> {
        val bookOptional: Optional<Book> = bookService.getBookById(id) // Optional<Book> 타입으로 지정
        return bookOptional.map { book -> ResponseEntity.ok(book) } // 매개변수 타입을 명시
                .orElse(ResponseEntity.notFound().build())
    }

    // 도서 추가
    @PostMapping
    fun addBook(@RequestBody book: Book): ResponseEntity<Book> {
        val createBook = bookService.addBook(book)
        return ResponseEntity.status(HttpStatus.CREATED).body(createBook)
    }

    // 도서 수정
    @PutMapping("/{id}")
    fun updateBook(@PathVariable id: Long, @RequestBody updateBook: Book): ResponseEntity<Book> {
        val updatedBook: Optional<Book> = bookService.updateBook(id, updateBook) // Optional<Book> 타입 지정
        return updatedBook.map { ResponseEntity.ok(it) }
                .orElse(ResponseEntity.notFound().build())
    }


    // 도서 삭제
    fun deleteBook(@PathVariable id: Long): ResponseEntity<Void> {
        return if (bookService.deleteBook(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}