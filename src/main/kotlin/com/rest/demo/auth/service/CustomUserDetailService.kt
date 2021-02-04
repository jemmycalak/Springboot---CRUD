package com.rest.demo.auth.service

import com.rest.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
* this class for setup custom user that will be authenticated
* require class {@link AuthConfiguration}
*/
@Service
class CustomUserDetailService: UserDetailsService{

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findUserByEmail(username!!)

        return User(user.email, user.password, listOf())
    }
}
