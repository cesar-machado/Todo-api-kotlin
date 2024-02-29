package com.cmach.todoapikotlin.service



import com.cmach.todoapikotlin.dto.AuthRegisterDTO
import com.cmach.todoapikotlin.model.User
import com.cmach.todoapikotlin.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService (
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JWTService,
    private val authManager: AuthenticationManager
) {
    private val logger = LoggerFactory.getLogger(AuthService::class.java)

    fun register(
        authRegister : AuthRegisterDTO
    ): String {
        val encode2 = BCryptPasswordEncoder().encode(authRegister.password)
//        logger.info("Encoded Pass $encode2")
        val user = User(UUID.randomUUID(),authRegister.email, encode2)
        userRepository.save(user)
        return "user added"
    }

    fun login(
        authRegister : AuthRegisterDTO
    ): String {
//        logger.info("Auth Service ${authRegister.email}")

        authManager.authenticate(UsernamePasswordAuthenticationToken(authRegister.email, authRegister.password))
        val user: User = userRepository.findUserByEmail(authRegister.email)

        return jwtService.generateToken(authRegister.email)
    }

}