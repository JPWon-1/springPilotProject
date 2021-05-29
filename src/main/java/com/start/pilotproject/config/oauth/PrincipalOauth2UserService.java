// package com.start.pilotproject.config.oauth;

// import java.util.Map;

// import com.start.pilotproject.config.auth.PrincipalDetails;
// import com.start.pilotproject.config.oauth.provider.GoogleUserInfo;
// import com.start.pilotproject.config.oauth.provider.NaverUserInfo;
// import com.start.pilotproject.config.oauth.provider.OAuth2UserInfo;
// import com.start.pilotproject.domain.member.Member;
// import com.start.pilotproject.domain.member.Role;
// import com.start.pilotproject.repository.member.MemberRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
// import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
// import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.stereotype.Service;

// @Service
// public class PrincipalOauth2UserService extends DefaultOAuth2UserService{

//     @Autowired
//     private PasswordEncoder bCryptPasswordEncoder;

//     @Autowired
//     private MemberRepository memberRepository;

//     //구글로부터 받은 userRequest데이터에 대한 후처리되는 함수
//     //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다!
//     @Override
//     public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//         OAuth2User oauth2User = super.loadUser(userRequest);
//         //구글로그인버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> 코드 리턴(Oauth-Client라이브러리) ->AccessToken요청
//         //userRequest정보 -> loadUser함수 -> 구글로부터 회원 프로필을 받아준다.
//         //userRequest에는 resgistrationId가 있는데 이를 통해 어떤 oAuth로 로그인 했는지 알 수 있음
//         //getAttributes = oauth2User.getAttributes()
//         //oauth로 로그인 했을 때 회원가입 시키기
//         OAuth2UserInfo oAuth2UserInfo = null;
//         if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
//             //구글 로그인 요청
//             oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
//         }else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
//             oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response"));
//         }

//         String provider = oAuth2UserInfo.getProvider();
//         String providerId = oAuth2UserInfo.getProviderId();
//         String email = oAuth2UserInfo.getEmail();
//         String password = bCryptPasswordEncoder.encode("test");
//         Role role = Role.USER;
//         Member memberEntity = memberRepository.findByEmail(email);
//         if(memberEntity == null){
//             memberEntity = Member.builder()
//                     .email(email)
//                     .password(password)
//                     .role(role)
//                     .provider(provider)
//                     .providerId(providerId)
//                     .build();
//             memberRepository.save(memberEntity);
//         }

//         return new PrincipalDetails(memberEntity, oauth2User.getAttributes());
//     }
// }
