package com.start.pilotproject.config.jwt;

public interface JwtProperties {
    String SECRET = "SECRET_KEY";//우리 서버만 알고 있는 비밀 값
    int EXPIRATION_TIME = 60000*10;//1000 = 1초/60000 = 60초 = 1분, 60000*10 = 10분
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String SIGN_UP_URL = "/join";
}
