package com.cmach.todoapikotlin.controllers

import com.cmach.todoapikotlin.model.Hello
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController

class HelloController {

    val IdCounter: AtomicLong = AtomicLong()

    @RequestMapping("/hello")
    fun helloWorld(): Hello {
        return Hello(IdCounter.incrementAndGet(), "Hello, World")
    }
}