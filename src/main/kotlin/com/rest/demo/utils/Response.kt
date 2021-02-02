package com.rest.demo.utils

data class Response<T>(
    var status:Int,
    var message: String?,
    var data:T? = null
)
