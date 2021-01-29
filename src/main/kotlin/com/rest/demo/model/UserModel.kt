package com.rest.demo.model

import com.rest.demo.utils.anotation.password.ValidPassword
import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.CreationTimestamp
import java.sql.Date
import javax.persistence.*

import org.springframework.format.annotation.DateTimeFormat
import javax.validation.constraints.*


@Entity
@Table(name="users")
data class UserModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false, length = 50)
    @NotNull/*(message = "Name should not be empty")*/
    @NotEmpty
    @Pattern(regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")
    var name: String,

    @NotNull/*(message = "Name should not be empty")*/
    @NotEmpty
    @Email/*(message = "Email should not be empty")*/
    @Pattern(regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")
    var email: String,

    @NotNull/*(message = "Name should not be empty")*/
    @NotEmpty
    @ValidPassword
    var password: String
//    @CreationTimestamp
//    var createAt: Date?,
//    @CreationTimestamp
//    var updateAt: Date?,
)

class Customer {
    @Size(min = 2, max = 30)
    var name: String? = null
    var email: @NotEmpty @Email String? = null

    @Min(18)
    @Max(100)
    var age: @NotNull Int? = null
    var gender: @NotNull Gender? = null

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    var birthday: @NotNull @Past Date? = null

//    @Phone
    var phone: String? = null

    enum class Gender {
        MALE, FEMALE
    }
}
