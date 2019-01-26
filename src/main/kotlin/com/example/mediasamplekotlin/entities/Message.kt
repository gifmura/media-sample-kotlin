package com.example.mediasamplekotlin.entities

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "message")
data class Message(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotBlank
    val content: String = ""
)