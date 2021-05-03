package com.start.pilotproject.config;

import javax.sql.DataSource;

import com.start.pilotproject.domain.account.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable().and()
        .authorizeRequests()
            .antMatchers("/","/css/**","/scss/**","/img/**","/js/**","/h2-console/**").permitAll()
            .antMatchers("/posts","/create").permitAll()
            .antMatchers("/api/v1/**").hasRole(Role.USER.name())    
            .anyRequest().authenticated().and()
        .formLogin()
            .loginPage("/login")
            .permitAll().and()
        .logout()
            .logoutSuccessUrl("/");
    }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.jdbcAuthentication()
    //         .dataSource(dataSource)
    //         .passwordEncoder(passwordEncoder())
    //         .usersByUsernameQuery("select name, email, password, role from account where email =?")
    //         .authoritiesByUsernameQuery("select email, role from account where email=?");
    // }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication()
    //         .withUser("user") // user 계정을 생성했다. 이부분에 로그인아이디가 된다.
    //         .password(passwordEncoder().encode("1234")) // passwordEncoder 로 등록 한 인코더로 1234 를 암호화한다.
    //         .roles("USER"); // 유저에게 USER 라는 역할을 제공한다.*/
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
