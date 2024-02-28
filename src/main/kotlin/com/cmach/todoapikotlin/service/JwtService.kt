package com.cmach.todoapikotlin.service


import com.cmach.todoapikotlin.config.JwtEnv
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

@Service
class JWTService(
    jwtEnv: JwtEnv
) {
        private val SECRET = jwtEnv.key


    fun generateToken(email: String): String {
        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET)))
            .setSubject(email)
            .setIssuedAt(Date()) // Use Kotlin's built-in Date() function
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 24 * 3))
            .compact()
    }

    fun decode(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET)))
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun isExpired(token: String): Boolean {
        return decode(token).expiration.before(Date())
    }

}
