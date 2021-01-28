package com.rest.demo.repository

import com.rest.demo.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserModel, Long> {

}
