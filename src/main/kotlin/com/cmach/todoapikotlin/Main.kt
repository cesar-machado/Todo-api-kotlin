package com.cmach.todoapikotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ConfigurationPropertiesScan
@ComponentScan(basePackages = ["com.cmach"])
class Main


fun main(args: Array<String>) {
	runApplication<Main>(*args)
}
