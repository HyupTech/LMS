package com.example.lms

import com.example.lms.domain.repository.BookRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class LmsApplicationTests {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        val book = com.example.lms.domain.entity.Book(title = "Test Book", author = "Author Name", isRented = false)
        bookRepository.save(book)
    }


    @Test
    fun `get all books should return OK`() {
        mockMvc.get("/books")
                .andExpect {
                    status { isOk() }
                    jsonPath("$.length()") { isNumber() }
                }
    }

    @Test
    fun `get book by id should return OK`() {
        mockMvc.get("/books/1")
                .andExpect {
                    status { isOk() }
                    jsonPath("$.id") { value(1) }
                }
    }

    @Test
    fun `get book by non-existent id should return Not Found`() {
        mockMvc.get("/books/9999")
                .andExpect {
                    status { isNotFound() }
                }
    }

}
