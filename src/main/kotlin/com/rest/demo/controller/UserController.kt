package com.rest.demo.controller

import com.rest.demo.model.UserModel
import com.rest.demo.service.UserService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@Slf4j
class UserController {

    @Autowired
    lateinit var service: UserService

    @PostMapping
    fun createUser(@RequestBody body: UserModel): ResponseEntity<UserModel> {
        return ResponseEntity.ok().body(service.createUser(body))
    }

    @GetMapping
    fun getUsers(): ResponseEntity<List<UserModel>> = ResponseEntity.ok().body(
        service.getUsers()
    )

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserModel> =
        ResponseEntity.ok().body(service.getUserById(id))

    @PutMapping
    fun updateUser(@RequestBody body: UserModel): ResponseEntity<UserModel> =
        ResponseEntity.ok().body(service.updateUser(body))

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): HttpStatus {
        service.deteleteUser(id)
        return HttpStatus.NO_CONTENT
    }

}
