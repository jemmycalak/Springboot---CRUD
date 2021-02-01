package com.rest.demo.entity

import javax.persistence.*
import javax.validation.constraints.*

@Entity
@Table(name="users")
data class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @field:NotNull(message = "Name should not be null")
    @field:NotEmpty(message = "name empty")
    @field:NotBlank(message = "name blank")
    @field:Size(min=3, message = "name should min 3 character")
    var name: String?,

    @field:NotNull(message = "Name should not be empty")
    @field:Size(min=3, message = "email should min 3 character")
    @field:Email(message = "email not valid")
    var email: String?,

    @field:NotNull(message = "password should not be empty")
    @field:NotEmpty
    @field:NotBlank(message = "password should not be blank")
    @field:Pattern(regexp ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$", message = "password regex fail")
    var password: String?

//    @CreationTimestamp
//    var createAt: Date?,
//    @CreationTimestamp
//    var updateAt: Date?,
)
