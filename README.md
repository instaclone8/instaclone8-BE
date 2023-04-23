## 항해99 13기 미니프로젝트 8조 Backend github

## 🏛 프로젝트 주제
SeoulCulturePort (서울문화행사 조회 서비스)

* 서울시 문화 행사 검색과 리뷰 작성 

📅 프로젝트 기간 : 2023.03.24 ~ 2023.03.30

## Back End Team
|장동희|류준영|홍다정|
|:---:|:---:|:---:|
|@DongHee980630|@SLIPPECAT|@bambee83|
|BE|BE|BE|BE|

## 🛠 개발 환경 | 개발 도구 
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"/>  <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white"/>  <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat-square&logo=SpringSecurity&logoColor=white"/>  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/>  <img src="https://img.shields.io/badge/IntelliJIDEA-000000?style=flat-square&logo=IntelliJIDEA&logoColor=white"/>  <img src="https://img.shields.io/badge/github-181717?style=flat-square&logo=github&logoColor=white"/>  <img src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=git&logoColor=white"/>  <img src="https://img.shields.io/badge/Postman-FF6C37?style=flat-square&logo=Postman&logoColor=white"/> 

<img src="https://img.shields.io/badge/AmazonEC2-FF9900?style=flat-square&logo=AmazonEC2&logoColor=white"/>  <img src="https://img.shields.io/badge/AmazonS3-569A31?style=flat-square&logo=AmazonS3&logoColor=white"/>  <img src="https://img.shields.io/badge/AmazonRDS-527FFF?style=flat-square&logo=AmazonRDS&logoColor=white"/>  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>  <img src="https://img.shields.io/badge/Ubuntu-E95420?style=flat-square&logo=Ubuntu&logoColor=white"/>

## [📋 팀 노션, API 명세서](https://www.notion.so/5-SA-f4ebf090ac43441f88ff063a6ee7cd78?pvs=4)

## 📲 프로젝트 기능
1. 메인페이지

    * 어떤 문화 행사가 있는지 조회
    * 게시글 검색과 게시글 쓰기

2. 회원가입, 로그인

    * JWT토큰과 Spring Security를 적용

3. 개인정보 수정

    * 비밀번호와 닉네임 수정

4. 마이페이지

    * 내가 쓴 게시글만 모아서 조회

5. 게시글 상세페이지

    * 게시글 마다의 상세내용을 조회
    * 게시글에 대한 좋아요 기능
    * 게시글에 대한 댓글 쓰기

## 📋 ERD
![스크린샷 2023-03-23 오전 10 59 45](https://user-images.githubusercontent.com/99319021/227106520-e7ec40fa-73a4-49de-9121-7104a1cbc300.png)

## ⚽ 트러블 슈팅

 1. 개인 계정마다 게시글, 댓글에 대한 좋아요 여부 판단 로직
    * 사용자는 로그인 후 상세게시글에 진입을 하면 내가 게시글을 좋아요를 눌렀는지 안했는지 알 수 있어야 한다. 
    * 좋아요 기능 추가 이전에는 상세페이지에 토큰 없이 진행을 했었지만 좋아요 기능을 넣으면서 토큰을 받고 사용자 개인들마다 좋아요 정보를 넘겨주어야한다.
    * JAP 연관관계를 수정하면서 단방향으로 연관관계 적용할때 레포지토리에 저장이 안되는건지 레포지토리에서 데이터를 제대로 불러오지 못해서 그런건지 서비스 로직이 작동을 안해서 양방향 연관관계로 수정했음.
    * 사용자 정보를 판단하기 위해서 board, comment responseDto 생성자 if 사용자 여부 판단 로직 추가함

2. CICD 자동배포 민감한 정보 숨기기
    * 자동배포를 할때 MySql 엔드포인트, 아이디, 비밀번호를 추가해서 보내야하는데 그렇게 되면 깃허브에 개인정보가 노출이 되기 때문에 따로 숨기처리를하여 배포해야한다. 그래서 해결방법은
    * Actions secrets and variables에 MySql 접속 코드를 넣고 workflows에서 배포가 시작되면 자동으로 접속코드가 들어있는 코드들을 application.properties 주입 받는 형식으로 수정했습니다.

3. CORS 문제 해결
    * 프로젝트 시작 초기에 로그인, 회원가입을 구현 후 배포를 시작하고 처음으로 프론트분들과 접속을 시도했을때 CORS때문에 접속이 안되는 현상을 처음으로 경험했고 구글링과 세션강의를 통해서 해결방법을 찾았습니다.
    * http.cors() 로 스프링 시큐리티에서 CORS 설정을 사용할 수 있도록 활성화 하고 프론트분들이 사용하고 도메인의 접근을 허용하는 URL을 추가하는 설정을 했습니다. 추가적으로 액세스 가능한 헤더, 액세스 가능한 HTTP메서드도 설정했습니다. 
 
4. API 명세서에 작성에 대한 중요성
    * 엔티티 컬럼이 많다보니까 완성 이후 responsedto를 변경해야 할 일들이 많아졌다 사소한 작업이기는 했지만 아무래도 앞으로는 모든 data를 넘겨주는 게 좋겠다는 생각이 들었다
    * 예외처리와 상태코드를 받는 게 FE에서 중요한 일 인 것 같아서 잘 처리하고 API명세서에도 기록해주어야 한다는 것을 배웠습니다  + camelcase 같은 사소 한 것들도 잘 표기해서 보내드리기
