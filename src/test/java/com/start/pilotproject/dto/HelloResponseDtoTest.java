package com.start.pilotproject.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.start.pilotproject.web.dto.HelloResponseDto;

import org.junit.jupiter.api.Test;

public class HelloResponseDtoTest {
   @Test
   public void 롬복_기능_테스트(){
       //given
       String name = "test";
       int amount = 1000;
       //when
       HelloResponseDto dto = new HelloResponseDto(name,amount);
       //then
       assertThat(dto.getName()).isEqualTo(name);//assertj 의 assertThat을 사용!
       assertThat(dto.getAmount()).isEqualTo(amount);//assertj 의 assertThat을 사용!
   } 
    
}