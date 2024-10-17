package com.example.demo.domain.entities

import com.example.demo.domain.enums.Role
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "user")
data class UserEntity(
    @Id
    @Column(name = "id")
    val id: UUID,
    @Column(name = "email")
    val email: String,
    @Column(name = "password")
    val password: String,
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    val role: Role
)
