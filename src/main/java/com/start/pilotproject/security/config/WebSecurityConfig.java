package com.start.pilotproject.security.config;

import com.start.pilotproject.domain.member.Role;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()//post를 보낼수 있게 해줌.
            .headers().frameOptions().disable().and()
        .authorizeRequests()
            .antMatchers("/","/css/**","/scss/**","/img/**","/js/**","/h2-console/**").permitAll()
            // .antMatchers("/posts").permitAll()
            .antMatchers("/join").permitAll()    
            // .antMatchers("/api/v1/**").hasRole(Role.USER.name())    
            // .anyRequest().authenticated().and()
            .anyRequest().permitAll().and()
        .formLogin()
            // .loginPage("/login")
            .usernameParameter("email")
            .permitAll().and()
        .logout()
            .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
