package com.example.demo.domain.dto

data class AuthenticationResponseDto(
    val accessToken: String,
    val refreshToken: String
)
