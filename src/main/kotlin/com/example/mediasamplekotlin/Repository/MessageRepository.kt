package com.example.mediasamplekotlin.Repository

import com.example.mediasamplekotlin.Entity.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Long>