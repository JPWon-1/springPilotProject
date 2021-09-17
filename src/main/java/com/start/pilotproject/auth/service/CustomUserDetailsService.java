package com.start.pilotproject.auth.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import com.start.pilotproject.domain.member.Member;
import com.start.pilotproject.repository.member.MemberRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
   private final MemberRepository memberRepository;

   public CustomUserDetailsService(MemberRepository userRepository) {
      this.memberRepository = userRepository;
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String email) {
      return memberRepository.findByEmail(email)
         .map(member -> createUser(email, member))
         .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
   }

   private org.springframework.security.core.userdetails.User createUser(String username, Member member) {
      if (member.getLocked()) {
         throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
      }
      Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
      grantedAuthorities.add(new GrantedAuthority(){
          @Override
          public String getAuthority() {
              return member.getRole().getKey();
          }
      });
      return new org.springframework.security.core.userdetails.User(member.getEmail(),member.getPassword(),
              grantedAuthorities);
   }
}