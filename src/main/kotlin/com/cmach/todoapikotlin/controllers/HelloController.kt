package com.cmach.todoapikotlin.controllers

import com.cmach.todoapikotlin.model.Hello
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
@CrossOrigin(origins = ["http://localhost:5173/"])
class HelloController {

    @RequestMapping("/hello")
    fun helloWorld(): Hello {
        return Hello(1, "Hello, World")
    }
}