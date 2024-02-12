package com.cmach.todoapikotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoApiKotlinApplication

fun main(args: Array<String>) {
	runApplication<TodoApiKotlinApplication>(*args)
}
