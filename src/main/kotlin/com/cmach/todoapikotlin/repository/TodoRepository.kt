package com.cmach.todoapikotlin.repository

import com.cmach.todoapikotlin.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo, String> {
}