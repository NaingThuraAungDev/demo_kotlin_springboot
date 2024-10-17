package com.example.demo.services

import com.example.demo.domain.entities.UserEntity
import com.example.demo.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun createUser(userEntity: UserEntity): UserEntity {
        return userRepository.save(userEntity)
    }

    fun getUser(): List<UserEntity> {
        return userRepository.findAll()
    }
}