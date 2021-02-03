package com.rest.demo.controller

import com.rest.demo.auth.service.JwtUtils
import com.rest.demo.models.LoginModel
import com.rest.demo.service.UserService
import com.rest.demo.utils.CustomeResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@RestController
class AuthController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtUtils: JwtUtils

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/")
    fun index(): String {
        return "welcome"
    }

    @PostMapping("/login")
    fun login(@RequestBody body: LoginModel): ResponseEntity<Any>{
        val response = try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                body.email,
                body.password
            ))

            CustomeResponse(
                status = HttpStatus.OK.value(),
                message = "success",
                data = jwtUtils.generateToken(User(body.email, body.password, listOf()))
            )
        }catch (e: Exception){
            CustomeResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = "email or password are wrong"
            )
        }

        return ResponseEntity(response, HttpStatus.OK)
    }
}
