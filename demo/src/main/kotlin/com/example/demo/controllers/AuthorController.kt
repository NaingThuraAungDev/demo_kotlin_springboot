package com.example.demo.controllers

import com.example.demo.domain.dto.AuthorDto
import com.example.demo.domain.dto.AuthorUpdateDto
import com.example.demo.domain.models.AuthorModel
import com.example.demo.services.AuthorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
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

@Tag(name = "Author", description = "Author setup")
@RestController
class AuthorController(private val authorService: AuthorService) {
    @Operation(summary = "To save new author", description = "endpoint to save new author", requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
        content = [
            Content(
                mediaType = "application/json",
                examples = [
                    ExampleObject(
                        name = "example request",
                        value = """
                                { "id": null,"name":"Naing","age":21,"description":"A very good author","image":"naing.png" }
                                """

                    )
                ]
            )
        ]
    ))
    @PostMapping("/v1/author")
    fun createAuthor(@RequestBody authorDto: AuthorDto): AuthorDto {
        return authorService.save(authorDto.toAuthorEntity()).toAuthorDto()
    }

    @Operation(summary = "Retrieve author list")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK",content = [
                Content(
                    mediaType = "application/json",
                    examples = [
                        ExampleObject(
                            name = "200 Success response example",
                            value = """[
                                { "id": 1,"name":"Naing","age":21,"description":"A very good author","image":"naing.png" },
                                { "id": 2,"name":"Thura","age":21,"description":"A very good author","image":"Thura.png" }
                                ]
                                """

                        )
                    ]
                )
            ]),
            ApiResponse(responseCode = "500", description = "Internal server error", content = [
                Content(
                    mediaType = "application/json",
                    examples = [
                        ExampleObject(
                            name = "500 Internal server error example",
                            value = """
                                { 
                                    "success": false,
                                    "data":null,
                                    "error":{
                                        "error_code": 500,
                                        "error_description":"Something wrong in server. Please contact your administrator"
                                    }
                                }
                                """

                        )
                    ]
                )
            ])]
    )
    @GetMapping("/v1/author")
    fun getAuthor(): List<AuthorDto> {
        return authorService.getList().map { it.toAuthorDto() }
    }

    @PutMapping("/v1/author")
    fun updateAuthor(@RequestBody updateAuthorDto: AuthorUpdateDto): AuthorDto {
        return authorService.updateAuthor(AuthorModel.ModelMapper.toModel(updateAuthorDto)).toAuthorDto()
    }

    @DeleteMapping("/v1/author/{id}")
    fun deleteAuthor(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        authorService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}