package com.example.demo.domain.dto

import com.example.demo.domain.enums.Role
import java.util.UUID

data class UserDto(
    var id: UUID?,
    val email: String,
    var password: String,
    val role: Role
)
