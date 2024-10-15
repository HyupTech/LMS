package com.example.lms.domain.repository

import com.example.lms.domain.entity.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long>