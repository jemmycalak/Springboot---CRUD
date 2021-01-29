package com.rest.demo.controller

import com.rest.demo.model.UserModel
import com.rest.demo.service.UserService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import javax.validation.Valid
import kotlin.io.path.Path

@RestController
@RequestMapping("/api/users")
@Validated
class UserController {

    @Autowired
    lateinit var service: UserService

    @PostMapping
    fun createUser(@Valid @RequestBody body: UserModel): ResponseEntity<UserModel> {
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

    @PatchMapping
    fun uploadFile(
        @RequestParam("file") file: MultipartFile,
    ): ResponseEntity<*> {
        val locationUploaded = "C://upload_image//"

        return if (file.isEmpty) {
            ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST)
        } else {
            val fileByte = file.bytes
            val path = Paths.get("$locationUploaded${file.originalFilename}")
            Files.write(path, fileByte)

            ResponseEntity.ok().body((HttpStatus.OK))
        }
    }

}
