# 스프링 부트 프로젝트

## gradle, thymeleaf, jdbc, querydsl, JUnit Test

 를 사용 했습니다. 

작업의 계획은

1. 게시판 작성
2. 유저 작성 ( 인증 , 인가 )
3. 유저 로그인 구현 - 소셜 로그인 추가 ( google, kakao, naver, github )
4. 배포

입니다

## 프로젝트 개발 상황

- 2021년 3월 10일 : 프로젝트 init
- 2021년 3월 11일 : Create test source & lombok 적용
- 2021년 3월 16일 : buildgradle 업데이트 (feat.querydsl)
- 2021년 3월 20일 : Template 적용( 온라인에서 무료 템플릿 찾아서 적용함 )
- 2021년 3월 23일 : BaseTimeEntity - modified,created time 자동 등록하기 위한 클래스 생성, Update html : 글쓰기, 등록 취소 버튼 생성
- 2021년 3월 25일 : Test코드 추가 : post update test
- 2021년 3월 29일 : Modify PostsUpdateRequestDto
- 2021년 3월 30일 : Update get : post method 추가
- 2021년 4월 07일 : Update get : post ( feat. querydsl ) querydsl 을 이용한 get:post 추가
- 2021년 4월 31일 : Modify Post Test Code & DTO : test코드에서 정상 작동 안하던 것 수정 및 dto에서 쓸데없는 builder패턴을 뺏음. 그리고 공통된 부분들 fragments로 뺏음. (sidebar, navbar)
- 2021년 5월 02일 : feat user join test : 간단하게 String email, String password를 입력한 후 회원가입을 하는 유저 테스트를 작성 함. 이후에는 이메일 밸리데이션, 회원가입 밸리데이션 등을 추가해야 함. 그 외에 dto를 controller와 같은 폴더에 두었음. 그리고 repository 폴더를 생성하여 controller, service , repository , domain 의 4가지 폴더로 구분하여 좀 더 프로젝트를 보기 쉽게 작성함.
