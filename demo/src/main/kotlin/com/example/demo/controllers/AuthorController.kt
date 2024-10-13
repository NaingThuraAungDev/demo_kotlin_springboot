package com.example.demo.controllers

import com.example.demo.domain.dto.AuthorDto
import com.example.demo.domain.dto.AuthorUpdateDto
import com.example.demo.domain.models.AuthorModel
import com.example.demo.services.AuthorService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import toAuthorDto
import toAuthorEntity

@RestController
class AuthorController(private val authorService: AuthorService) {
    @PostMapping("/v1/author")
    fun createAuthor(@RequestBody authorDto: AuthorDto): AuthorDto{
        return  authorService.save(authorDto.toAuthorEntity()).toAuthorDto()
    }
    @GetMapping("/v1/author")
    fun getAuthor(): List<AuthorDto>{
        return authorService.getList().map { it.toAuthorDto() }
    }
    @PutMapping("/v1/author")
    fun updateAuthor(@RequestBody updateAuthorDto: AuthorUpdateDto): AuthorDto{
        return  authorService.updateAuthor(AuthorModel.ModelMapper.toModel(updateAuthorDto)).toAuthorDto()
    }
    @DeleteMapping("/v1/author/{id}")
    fun deleteAuthor(@PathVariable("id") id: Long): ResponseEntity<Unit>{
        authorService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}