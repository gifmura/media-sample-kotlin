package com.example.mediasamplekotlin.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class LoginController {

    @RequestMapping(value = "/admin")
    fun admin(): String {
        return "admin"
    }

    @RequestMapping(value = "/login")
    fun login(): String {
        return "login"
    }

    @RequestMapping(value = "/403")
    fun error403(): String {
        return "error_403"
    }
}