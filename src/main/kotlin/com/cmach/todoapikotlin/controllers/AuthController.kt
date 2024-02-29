package com.cmach.todoapikotlin.controllers

import com.cmach.todoapikotlin.dto.AuthRegisterDTO
import com.cmach.todoapikotlin.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    private val logger = LoggerFactory.getLogger(AuthController::class.java)

    @PostMapping("/register")
    fun register(
        @RequestBody authRegisterDTO: AuthRegisterDTO

    ): String {
//        logger.info("O que veio $authRegisterDTO")
        return authService.register(authRegisterDTO)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody authRegisterDTO: AuthRegisterDTO
    ): String {
//        logger.info("O que veio $authRegisterDTO")
        return authService.login(authRegisterDTO)
    }

    @GetMapping("/admin")
    fun test(): String{
        return "hello for admins"
    }
}