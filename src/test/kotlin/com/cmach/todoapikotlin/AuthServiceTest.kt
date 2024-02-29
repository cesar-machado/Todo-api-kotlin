package com.cmach.todoapikotlin

import com.cmach.todoapikotlin.dto.AuthRegisterDTO
import com.cmach.todoapikotlin.model.User
import com.cmach.todoapikotlin.repository.UserRepository
import com.cmach.todoapikotlin.service.AuthService
import com.cmach.todoapikotlin.service.JWTService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder

class AuthServiceTest {
    private val userRepositoryMock: UserRepository = mock()
    private val passwordEncoderMock: PasswordEncoder = mock()
    private val authManagerMock: AuthenticationManager = mock()
    private val jwtServiceMock: JWTService = mock()

    @Test
    fun `should save user and return success message when registering with valid credentials`() {
        val email = "test@example.com"
        val password = "password123"
        val encodedPassword = "hashedPassword"
        `when`(passwordEncoderMock.encode(password)).thenReturn(encodedPassword)

        val authService = AuthService(
            userRepositoryMock,
            passwordEncoderMock,
            jwtServiceMock,
            authManagerMock
        )

        val authRegister = AuthRegisterDTO(email, password)

        val response = authService.register(authRegister)

        verify(userRepositoryMock).save(User(any(), email, encodedPassword))

        Assertions.assertEquals("user added", response)
    }

    @Test
    fun `should generate token when logging in with valid credentials`() {
        val email = "test@example.com"
        val password = "password123"
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(email, password)
        val mockUserDetails: UserDetails = mock()
        val token = "generated_token"

        `when`(authManagerMock.authenticate(usernamePasswordAuthenticationToken)).thenReturn(
            mockAuthentication(
                mockUserDetails
            )
        )
        `when`(userRepositoryMock.findUserByEmail(email)).thenReturn(userFromUserDetails(mockUserDetails))
        `when`(jwtServiceMock.generateToken(email)).thenReturn(token)

        val authService = AuthService(
            userRepositoryMock,
            passwordEncoderMock,
            jwtServiceMock,
            authManagerMock
        )

        val authRegister = AuthRegisterDTO(email, password)

        val returnedToken = authService.login(authRegister)

        verify(authManagerMock).authenticate(usernamePasswordAuthenticationToken)
        verify(userRepositoryMock).findUserByEmail(email)
        verify(jwtServiceMock).generateToken(email)

        Assertions.assertEquals(token, returnedToken)
    }

    private fun mockAuthentication(userDetails: UserDetails): Authentication {
        return mock()
    }

    private fun userFromUserDetails(userDetails: UserDetails): User {
        return User(any(), "test@example.com", "password123")
    }
}