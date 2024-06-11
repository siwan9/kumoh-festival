package kr.ac.kumoh.ce.s20200237.databaseProject.global

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun configure(http: HttpSecurity) : SecurityFilterChain {
        http
                .csrf { csrf -> csrf.disable() }
                .formLogin{formLogin -> formLogin.disable()}
                .sessionManagement{auth -> auth.maximumSessions(1)
                        .maxSessionsPreventsLogin(false)}
                .authorizeHttpRequests{authorizeRequests -> authorizeRequests.anyRequest().permitAll()}
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}