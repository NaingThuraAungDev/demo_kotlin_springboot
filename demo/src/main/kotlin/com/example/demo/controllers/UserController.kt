package com.example.demo.controllers

import com.example.demo.domain.dto.UserDto
import com.example.demo.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toUserDto
import toUserEntity
import java.util.UUID

@RestController
@RequestMapping(path = ["/v1/user"])
class UserController(
    private val userService: UserService,
    private val encoder: PasswordEncoder
) {
    @PostMapping
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        userDto.id = UUID.randomUUID()
        val encodedPassword =  encoder.encode(userDto.password)
        userDto.password = encodedPassword
        val user = userService.createUser(userDto.toUserEntity()).toUserDto()
        return ResponseEntity(user, HttpStatus.CREATED)
    }

    @GetMapping
    fun userList(): ResponseEntity<List<UserDto>>{
        val userList = userService.getUser().map { it.toUserDto() }
        return ResponseEntity(userList,HttpStatus.OK)
    }
}