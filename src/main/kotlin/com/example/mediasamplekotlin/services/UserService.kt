package com.example.mediasamplekotlin.services

import com.example.mediasamplekotlin.entities.Role
import com.example.mediasamplekotlin.entities.User
import com.example.mediasamplekotlin.repositories.RoleRepository
import com.example.mediasamplekotlin.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service("userService")
class UserService @Autowired constructor(
    private val userRepo: UserRepository,
    private val roleRepo: RoleRepository
) {

    fun findByEmail(email: String): User? = userRepo.findByEmail(email)

    @Throws(RuntimeException::class)
    fun saveAdminUser(user: User):Unit {
        val role = roleRepo.findByName(Role.ADMIN) ?: throw RuntimeException("undefined role was called.")
        user.roles += listOf(role)
        user.password = BCryptPasswordEncoder().encode(user.password)
        userRepo.save(user)
    }

    @Throws(RuntimeException::class)
    fun saveUser(user: User):Unit {
        val role = roleRepo.findByName(Role.USER) ?: throw RuntimeException("undefined role was called.")
        user.roles += listOf(role)
        user.password = BCryptPasswordEncoder().encode(user.password)
        userRepo.save(user)
    }
}