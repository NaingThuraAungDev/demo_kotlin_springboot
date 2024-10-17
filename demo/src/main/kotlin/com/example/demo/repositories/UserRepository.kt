package com.example.demo.repositories

import com.example.demo.domain.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity,String> {
    fun findByEmail(email: String): UserEntity?
}