package com.rest.demo.utils

data class CustomeResponse<T>(
    var status:Int,
    var message: String?,
    var data:T? = null
)
