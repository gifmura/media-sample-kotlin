package com.example.mediasamplekotlin.entities

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "role")
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotBlank
    val name: String = ""
){
    companion object {
        const val USER: String = "USER"
        const val ADMIN: String = "ADMIN"
    }
}