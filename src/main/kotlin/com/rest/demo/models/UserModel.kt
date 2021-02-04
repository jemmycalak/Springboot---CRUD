package com.rest.demo.models

import com.rest.demo.constant.Constants
import javax.validation.constraints.*

/*
* custom request body, can be use for validation body request
* */
data class UserModel (
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
    @field:Pattern(regexp = Constants.regexPassword, message = "password regex fail")
    var password: String?

)
