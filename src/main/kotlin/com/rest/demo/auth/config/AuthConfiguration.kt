package com.rest.demo.auth.config

import com.rest.demo.auth.service.CustomUserDetailService
import com.rest.demo.auth.service.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/*
* this class for setup authorization header
* */
@Configuration
@EnableWebSecurity
class AuthConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var customUserDetailService: CustomUserDetailService
    @Autowired
    private lateinit var jwtFilter: JwtFilter

    override fun configure(auth: AuthenticationManagerBuilder?) {
//        super.configure(auth)
        auth!!.userDetailsService(customUserDetailService)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean/*(name = [BeanIds.AUTHENTICATION_MANAGER])*/
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {
        http!!.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/login")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}
