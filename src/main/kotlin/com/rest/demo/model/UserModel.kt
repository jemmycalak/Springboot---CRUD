package com.rest.demo.model

import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.CreationTimestamp
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name="users")
@Setter
@Getter
data class UserModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(nullable = false, length = 50)
    var name: String,
    var email: String,
    var password: String
//    @CreationTimestamp
//    var createAt: Date?,
//    @CreationTimestamp
//    var updateAt: Date?,
)
