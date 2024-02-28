package com.cmach.todoapikotlin.service

import com.cmach.todoapikotlin.model.User
import com.cmach.todoapikotlin.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findByEmail(email: String): User? {
        return this.userRepository.findUserByEmail(email)
    }
}