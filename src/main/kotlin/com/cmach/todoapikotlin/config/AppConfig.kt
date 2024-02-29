package com.cmach.todoapikotlin.config



import com.cmach.todoapikotlin.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class AppConfig(private val userRepository: UserRepository) {

    @Bean
    fun passEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder();


    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username -> userRepository.findUserByEmail(username) }
    }


    @Bean
    fun authProvider(): AuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passEncoder())
        daoAuthenticationProvider.setUserDetailsService(userDetailsService())

        return daoAuthenticationProvider
    }

    @Throws(Exception::class)
    protected fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authProvider())
    }

    @Bean
    fun authManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager;
    }
}