package com.cmach.todoapikotlin.controllers

import com.cmach.todoapikotlin.model.Todo
import com.cmach.todoapikotlin.repository.TodoRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(private val todoRepository: TodoRepository) {

    @GetMapping
    fun getAllTodos(): List<Todo> = todoRepository.findAll()


    @PostMapping
    fun createTodo(@RequestBody todo: Todo): Todo = todoRepository.save(todo)

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable id: String, @RequestBody updatedTodo: Todo): Todo {
        val existingTodo = todoRepository.findById(id).orElseThrow { NoSuchElementException("Todo not found") }
        existingTodo.name  = updatedTodo.name
        existingTodo.content = updatedTodo.content
        return todoRepository.save(existingTodo)
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: String) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id)
        } else {
            throw NoSuchElementException("Todo not found")
        }
    }
}