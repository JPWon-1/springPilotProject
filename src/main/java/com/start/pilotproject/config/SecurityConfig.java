package com.start.pilotproject.config;

import com.start.pilotproject.config.jwt.JwtAuthenticationFilter;
import com.start.pilotproject.config.jwt.JwtAuthorizationFilter;
import com.start.pilotproject.domain.member.Role;
import com.start.pilotproject.repository.member.MemberRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final MemberRepository memberRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//세션을 사용하지 않는 정적인 서버로 만든다
        .addFilter(corsFilter) // 모든 요청은 이 필터를 타게 된다 //@CrossOrigin(인증 없을때 쓴다) <-> 인증이 있을때는 시큐리티 필터에 등록해야 한다.
        .formLogin().disable()
        .httpBasic().disable()
        .addFilter(new JwtAuthenticationFilter(authenticationManager()))//formLogin.disable해놓았기에 addFilter로 userNamePasswordAuthenticationFilter를 직접 활성화 시켜줌 
        .addFilter(new JwtAuthorizationFilter(authenticationManager(),memberRepository))
        .authorizeRequests()
            .antMatchers("/","/css/**","/scss/**","/img/**","/js/**","/h2-console/**").permitAll()
            .antMatchers("/api/v1/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
            .antMatchers("/api/v1/manager/**").hasRole(Role.ADMIN.name())
            .antMatchers("/posts/write").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
