package com.example.demo.domain.models

import com.example.demo.domain.dto.AuthorUpdateDto

data class AuthorModel(
    val id: Long,
    val name: String,
    val age: Int,
    val description: String? = null,
    val image: String? = null
) {
    object ModelMapper {
        fun toModel(dto: AuthorUpdateDto) = AuthorModel(
            id = dto.id,
            name = dto.name,
            age = dto.age,
            description = dto.description,
            image = dto.image
        )
    }
}