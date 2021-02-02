package com.rest.demo.repository

import com.rest.demo.entity.ApikeyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ApikeyRepository: JpaRepository<ApikeyEntity, String>
