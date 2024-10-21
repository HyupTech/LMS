package com.example.lms.controller

import Book
import Rental
import com.example.lms.service.RentalService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.util.*

@WebMvcTest(RentalController::class)
class RentalControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var rentalService: RentalService

    @Test
    fun `should create a new rental`() {
        val rental = Rental(
                id = 1L,
                book = Book(1L, "Sample Book", "Author"),
                rentalDate = LocalDate.of(2023, 10, 1),
                returnDate = null
        )

        Mockito.`when`(rentalService.addRental(any<Rental>()))
                .thenReturn(rental)

        val rentalRequest = mapOf(
                "bookId" to 1,
                "rentalDate" to "2023-10-01"
        )

        mockMvc.perform(
                post("/rentals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rentalRequest))
        ).andExpect(status().isCreated)
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.book.id").value(1))
                .andExpect(jsonPath("$.rentalDate").value("2023-10-01"))
                .andExpect(jsonPath("$.returnDate").doesNotExist())
    }

    @Test
    fun `should update an existing rental`() {
        val updatedRental = Rental(
                id = 1L,
                book = Book(1L, "Sample Book", "Author"),
                rentalDate = LocalDate.of(2023, 10, 1),
                returnDate = LocalDate.of(2023, 10, 10)
        )

        Mockito.`when`(
                rentalService.updateRental(eq(1L), any<Rental>())
        ).thenReturn(Optional.of(updatedRental))

        val rentalUpdateRequest = mapOf(
                "bookId" to 1,
                "rentalDate" to "2023-10-01",
                "returnDate" to "2023-10-10"
        )

        mockMvc.perform(
                put("/rentals/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rentalUpdateRequest))
        ).andExpect(status().isOk)
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.book.id").value(1))
                .andExpect(jsonPath("$.rentalDate").value("2023-10-01"))
                .andExpect(jsonPath("$.returnDate").value("2023-10-10"))
    }

    @Test
    fun `should delete a rental`() {
        Mockito.`when`(rentalService.deleteRental(1L)).thenReturn(true)

        mockMvc.perform(delete("/rentals/1"))
                .andExpect(status().isNoContent)
    }

    @Test
    fun `should return not found when deleting a non-existent rental`() {
        Mockito.`when`(rentalService.deleteRental(999L)).thenReturn(false)

        mockMvc.perform(delete("/rentals/999"))
                .andExpect(status().isNotFound)
    }
}
