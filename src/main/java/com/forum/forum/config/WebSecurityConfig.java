package com.forum.forum.config;

import com.forum.forum.dao.UserDao;
import com.forum.forum.model.User;
import com.forum.forum.other.enums.Role;
import com.forum.forum.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserDao userDao;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserDao userDao) {
        this.userDetailsService = userDetailsService;
        this.userDao = userDao;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/profile/{username}/delete").access("@webSecurity.checkUser(authentication,#username)")
                .antMatchers("/topic/{id}/delete").access("@webSecurity.checkTopic(authentication, #id)")
                .antMatchers("/topic/{topicId}/comment/{commentId}/delete").access("@webSecurity.checkComment(authentication, #commentId)")
                .antMatchers("/star/**","/vote/**", "/write", "/topic/{id}/comment").hasAuthority(Role.USER.getAuthority())
                .antMatchers("/admin/**").hasAuthority(Role.ADMIN.getAuthority())
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
