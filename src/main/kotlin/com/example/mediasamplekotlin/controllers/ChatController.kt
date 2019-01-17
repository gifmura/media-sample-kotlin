package com.example.mediasamplekotlin.controllers

import com.example.mediasamplekotlin.model.Message
import com.example.mediasamplekotlin.model.MessageRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid

/**
 * This controller featuring a basic CRUD.
 */
@Controller
class ChatController(private val messageRepository: MessageRepository){

    @GetMapping("/")
    fun index(): ModelAndView {return messages()}

    /**
     * READ
     */
    @RequestMapping("/users")
    fun messages(): ModelAndView = ModelAndView("/").apply { addObject("messages", messageRepository.findAll()) }

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
    fun update(@Valid @RequestBody message: Message): Message {return post(message)}

    /**
     * DELETE
     */
    @PostMapping("/delete")
    fun delete(@Valid @RequestBody id: Long): Unit =
            messageRepository.deleteById(id)
}