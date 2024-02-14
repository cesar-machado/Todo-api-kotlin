package com.cmach.todoapikotlin

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired lateinit var mockMvc: MockMvc

//    @Autowired lateinit var helloRepository: HelloRepository

    @Test
    fun `test find hello`() {
//        helloRepository.save(Hello(id = 1 , content = "Hello, World"))
        mockMvc.get("/hello")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("content") { value("Hello, World") }
            }
    }
}