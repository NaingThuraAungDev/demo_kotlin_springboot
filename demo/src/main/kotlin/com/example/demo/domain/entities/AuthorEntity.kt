package com.example.demo.domain.entities

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "author")
data class AuthorEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "name")
    val name: String,
    @Column(name = "age")
    val age: Int,
    @Column(name = "description")
    val description: String,
    @Column(name = "image")
    val image: String
): Serializable{
    companion object{
        private const val serialVersionUID = 1L
    }
}
