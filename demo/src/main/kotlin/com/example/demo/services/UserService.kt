package com.example.demo.services

import com.example.demo.domain.entities.UserEntity
import com.example.demo.repositories.UserRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun createUser(userEntity: UserEntity): UserEntity {
        return userRepository.save(userEntity)
    }

    @Async
    fun getUser(): CompletableFuture<List<UserEntity>> {
        return CompletableFuture.completedFuture(userRepository.findAll())
    }
}