package com.example.demo.repositories

import com.example.demo.domain.entities.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<BookEntity,String> {
}