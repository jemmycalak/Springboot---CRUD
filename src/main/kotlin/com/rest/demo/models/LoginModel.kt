package com.rest.demo.models

import com.rest.demo.constant.Constants
import javax.validation.constraints.*

class LoginModel(
    @field:NotNull(message = "Name should not be empty")
    @field:Size(min=3, message = "email should min 3 character")
    @field:Email(message = "email not valid")
    var email: String,

    @field:NotNull(message = "password should not be empty")
    @field:NotEmpty
    @field:NotBlank(message = "password should not be blank")
    @field:Pattern(regexp =Constants.regexPassword, message = "password regex fail")
    var password: String
)
