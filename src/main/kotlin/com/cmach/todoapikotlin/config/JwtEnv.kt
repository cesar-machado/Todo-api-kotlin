package com.cmach.todoapikotlin.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
data class JwtEnv(
    val key: String,
)