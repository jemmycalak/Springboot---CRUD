package com.rest.demo.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "api_keys")
data class ApikeyEntity(
    @Id
    val id: String
)
