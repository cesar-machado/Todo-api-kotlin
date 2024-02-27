package com.cmach.todoapikotlin.config

import com.cmach.todoapikotlin.config.filter.JwtFilter
import com.cmach.todoapikotlin.model.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfigs(
    private val jwtFilter: JwtFilter,
    private val authProvider: AuthenticationProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .cors{it.disable()}
            .authorizeHttpRequests{
                it.requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
                it.requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
                it.requestMatchers("/api/user").hasAuthority(Role.ADMIN.name)
                it.requestMatchers("/api/admin").hasAuthority(Role.ADMIN.name)
                it.requestMatchers(HttpMethod.POST,"/api/todos").hasAuthority(Role.ADMIN.name)
                it.requestMatchers(HttpMethod.PUT,"/api/todos").hasAuthority(Role.ADMIN.name)
                it.requestMatchers(HttpMethod.GET,"/api/todos").hasAuthority(Role.ADMIN.name)
                it.requestMatchers(HttpMethod.DELETE,"/api/todos").hasAuthority(Role.ADMIN.name)
//                it.requestMatchers(HttpMethod.POST,"/api/todos").hasAuthority(Role.ADMIN.name)
                it.anyRequest().authenticated()
            }
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}