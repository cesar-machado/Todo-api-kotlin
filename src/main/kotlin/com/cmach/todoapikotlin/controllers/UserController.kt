package com.cmach.todoapikotlin.controllers

import com.cmach.todoapikotlin.dto.UserDto
import com.cmach.todoapikotlin.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun test(): String {
        return "hello for users"
    }

    @GetMapping("/{email}")
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<UserDto> {
        val user = userService.findByEmail(email)
        return if (user != null) {
            val userDto = UserDto(user.id.toString(), user.email, user.role.toString())
            ResponseEntity.ok(userDto)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
