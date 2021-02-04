package com.rest.demo.repository

import com.rest.demo.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findUserByEmail(email: String): UserEntity
}
