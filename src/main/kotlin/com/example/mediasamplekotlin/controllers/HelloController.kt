package com.example.mediasamplekotlin.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloController{

    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    @ResponseBody
    fun helloWorld(): String {
        return "Hello, World!"
    }

    @RequestMapping(value = ["/hello/{name}"], method = [RequestMethod.GET])
    fun helloName(@PathVariable name: String, model: Model): String {
        model.addAttribute("name", name)
        return "hello_name"
    }
}