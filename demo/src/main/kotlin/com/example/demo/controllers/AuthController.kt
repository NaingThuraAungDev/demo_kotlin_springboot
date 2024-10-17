package com.example.demo.controllers

import com.example.demo.domain.dto.AuthenticationRequestDto
import com.example.demo.domain.dto.AuthenticationResponseDto
import com.example.demo.domain.dto.RefreshTokenRequestDto
import com.example.demo.domain.dto.RefreshTokenResponseDto
import com.example.demo.services.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping
    fun authenticate(
        @RequestBody authRequest: AuthenticationRequestDto
    ): AuthenticationResponseDto =
        authenticationService.authentication(authRequest)

    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequestDto
    ): RefreshTokenResponseDto =
        authenticationService.refreshAccessToken(request.token)
            ?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token.")

    private fun String.mapToTokenResponse(): RefreshTokenResponseDto =
        RefreshTokenResponseDto(
            token = this
        )
}