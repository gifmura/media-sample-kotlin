package com.example.mediasamplekotlin.services

import com.example.mediasamplekotlin.entities.User
import com.example.mediasamplekotlin.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import javax.transaction.Transactional

@Service
@Transactional
class UserDetailServiceImpl: UserDetailsService {

    @Autowired
    lateinit var userRepo: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user: User = userRepo!!.findByEmail(email) ?: throw UsernameNotFoundException("Email $email not found")
        return org.springframework.security.core.userdetails.User(user.email, user.password,getAuthorities(user))
    }

    private fun getAuthorities(user: User): Collection<GrantedAuthority> {
        val userRoles = user.roles.stream().map{ role -> role.name }.toArray<String>{ length -> arrayOfNulls(length) }
        return AuthorityUtils.createAuthorityList(*userRoles)
    }
}