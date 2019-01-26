package com.example.mediasamplekotlin.controllers

import com.example.mediasamplekotlin.entities.User
import com.example.mediasamplekotlin.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * authorization controller.
 */
@Controller
class UserController @Autowired constructor(private val userService: UserService) {

    /**
     * the form for user registration.
     */
    @RequestMapping(value = "/form", method = [RequestMethod.GET])
    fun form(model: Model): String {
        model.addAttribute("userForm", User())
        return "form"
    }

    /**
     * post request user registration.
     */
    @RequestMapping(value = "/signup", method = [RequestMethod.POST])
    fun signup(@ModelAttribute("userForm") userForm: User, bindingResult: BindingResult, model: Model): String {
        if (bindingResult.hasErrors()) {
            return "signup"
        }
        try {
            userService.saveUser(userForm)
        } catch (e: Exception) {
            return "error_417"
        }
        return "login"
    }

    /**
     * login form.
     */
    @RequestMapping(value = "/login", method = [RequestMethod.GET])
    fun login(): String {
        return "login"
    }

    /**
     * admin user page.
     * The user's role name is "ADMIN".
     */
    @RequestMapping(value = "/admin", method = [RequestMethod.GET])
    fun admin(): String {
        return "admin"
    }

    /**
     * login failure page.
    */
    @RequestMapping(value = "/403", method = [RequestMethod.GET])
    fun error403(): String {
        return "error_403"
    }
}