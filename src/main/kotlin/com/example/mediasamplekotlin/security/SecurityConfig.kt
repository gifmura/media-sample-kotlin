package com.example.mediasamplekotlin.security

import com.example.mediasamplekotlin.services.UserDetailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.sql.DataSource

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userDetailsServiceImpl: UserDetailServiceImpl

    @Autowired
    lateinit var dataSource: DataSource

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/hello").permitAll()
                .antMatchers("/hello/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("encrypted_password")
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(AntPathRequestMatcher("/logout**"))
                .logoutSuccessUrl("/login")
                .deleteCookies("my-remember-me-cookie")
                .permitAll()
                .and()
                .rememberMe()
                .rememberMeCookieName("my-remember-me-cookie")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60)
                .and()
                .exceptionHandling()

        http.exceptionHandling().accessDeniedPage("/error_403")
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(getPasswordEncoder())
//                .inMemoryAuthentication()
//                .withUser("user").password("{noop}user").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}admin").roles("ADMIN")
}

    fun persistentTokenRepository(): PersistentTokenRepository {
        val tokenRepositoryImpl = JdbcTokenRepositoryImpl()
        tokenRepositoryImpl.setDataSource(dataSource)
        return tokenRepositoryImpl
    }

}