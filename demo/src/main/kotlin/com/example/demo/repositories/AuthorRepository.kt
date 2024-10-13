package com.example.demo.repositories

import com.example.demo.domain.entities.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository: JpaRepository<AuthorEntity,Long?> {
}