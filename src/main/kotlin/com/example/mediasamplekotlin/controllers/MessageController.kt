package com.example.mediasamplekotlin.controllers

import com.example.mediasamplekotlin.Entity.Message
import com.example.mediasamplekotlin.Repository.MessageRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid

/**
 * This controller featuring a basic CRUD.
 */
@Controller
class MessageController(private val messageRepository: MessageRepository) {

    @GetMapping("/")
    fun index(): ModelAndView { return list() }

    /**
     * READ
     */
    @RequestMapping("/list")
    fun list(): ModelAndView = ModelAndView("/chat_room").apply { addObject("messages", messageRepository.findAll()) }

    /**
     * CREATE
     */
    @PostMapping("/post")
    fun post(@Valid @RequestBody message: Message): Message =
            messageRepository.save(message)

    /**
     * UPDATE
     */
    @PostMapping("/update")
    fun update(@Valid @RequestBody message: Message): Message { return post(message) }

    /**
     * DELETE
     */
    @PostMapping("/delete")
    fun delete(@Valid @RequestBody id: Long): Unit =
            messageRepository.deleteById(id)
}