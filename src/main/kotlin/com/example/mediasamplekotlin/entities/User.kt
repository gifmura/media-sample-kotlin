package com.example.mediasamplekotlin.entities

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotBlank
    @Email(message = "{errors.invalid_email}")
    val email: String = "",

    @get: NotBlank
    @Size(min = 4, max = 20)
    var password: String = "",

    @ManyToMany(cascade = [(CascadeType.ALL)])
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = [(JoinColumn(name = "user_id", referencedColumnName = "id"))],
            inverseJoinColumns = [(JoinColumn(name = "role_id", referencedColumnName = "id"))]
    )
    var roles: List<Role> = mutableListOf()
)