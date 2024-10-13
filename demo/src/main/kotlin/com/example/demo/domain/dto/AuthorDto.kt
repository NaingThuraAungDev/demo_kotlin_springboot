package com.example.demo.domain.dto

data class AuthorDto(
    val id: Long?,
    val name: String,
    val age: Int,
    val description: String,
    val image: String
)