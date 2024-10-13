package com.example.demo.domain.dto


data class AuthorUpdateDto(
    val id: Long,
    val name: String,
    val age: Int,
    val description: String? = null,
    val image: String? = null
)
