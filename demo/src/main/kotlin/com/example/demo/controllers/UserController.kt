package com.example.demo.controllers

import com.example.demo.domain.dto.UserDto
import com.example.demo.services.AuthorService
import com.example.demo.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import toUserDto
import toUserEntity
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping(path = ["/v1/user"])
class UserController(
    private val userService: UserService,
    private val encoder: PasswordEncoder,
    private  val logger: org.slf4j.Logger? = LoggerFactory.getLogger(AuthorService::class.java)
) {
    @PostMapping
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        userDto.id = UUID.randomUUID()
        val encodedPassword = encoder.encode(userDto.password)
        userDto.password = encodedPassword
        val user = userService.createUser(userDto.toUserEntity()).toUserDto()
        return ResponseEntity(user, HttpStatus.CREATED)
    }

    @GetMapping
    fun userList(): ResponseEntity<CompletableFuture<List<UserDto>>> {
        val userList = userService.getUserAsync().thenApply { x ->
            x.map {
                it.toUserDto()
            }
        }
        return ResponseEntity(userList,HttpStatus.OK)
    }
}