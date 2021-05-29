// package com.start.pilotproject.config;

// import com.start.pilotproject.config.oauth.PrincipalOauth2UserService;
// import com.start.pilotproject.domain.member.Role;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// @EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
// @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

//     @Autowired
//     private PrincipalOauth2UserService principalOauth2UserService;

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()//post를 보낼수 있게 해줌.
//             .headers().frameOptions().disable().and()
//         .authorizeRequests()
//             .antMatchers("/","/css/**","/scss/**","/img/**","/js/**","/h2-console/**").permitAll()
//             .antMatchers("/posts/write").hasRole(Role.USER.name())    
//             // .antMatchers("/api/v1/**").hasRole(Role.USER.name())    
//             .antMatchers("/join").permitAll()    
//             // .anyRequest().authenticated().and()
//             .anyRequest().permitAll().and()
//         .formLogin()
//             .loginPage("/login")
//             .loginProcessingUrl("/login")
//             .defaultSuccessUrl("/posts")
//             .usernameParameter("email")
//             .permitAll().and()
//         .logout()
//             .logoutSuccessUrl("/").and()
//         // oauth
//         //1. 코드 받기(인증)
//         //2. 엑세스토큰(권한)
//         //3. 사용자 프로필 정보를 가져옴
//         //4. 정보를 토대로 회원가입을 자동으로 진행시키기도 함.
//         //4-2. 구글에서 제공해주는 정보 외에 추가 정보가 필요 할 경우
//         //추가적인 회원창이 나와서 회원가입을 해야함
//         .oauth2Login()
//             .loginPage("/login")
//             .userInfoEndpoint()
//                 .userService(principalOauth2UserService)//로그인이 되면 액세스토큰 + 사용자 프로필 정보를 받음
//         ;
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// }
