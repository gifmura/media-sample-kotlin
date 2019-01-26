package com.example.mediasamplekotlin.repositories

import com.example.mediasamplekotlin.entities.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Long>