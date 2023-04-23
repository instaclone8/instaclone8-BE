##   🚀 인스타그램 클론코딩 (Instagram CloneCoding)

📝 프로젝트 소개 : 인스타그램 클론 코딩

📅 프로젝트 기간 : 2023.03.17 ~ 2023.03.23

👨‍👩‍👧‍👦  8조 : FE [송철환](https://github.com/SsongCh94) [김재란], BE [김동영] [류준영] [장동희] [홍다정](https://github.com/bambee83)

# [![Youtube Badge](https://img.shields.io/badge/Youtube-ff0000?style=flat-round&logo=youtube&link=https://youtu.be/vYJY0NuBx9Y)](https://youtu.be/vYJY0NuBx9Y)   [![Notion Badge](https://img.shields.io/badge/Notion-000000.svg?&style=flat-round&logo=notion&link=https://fragrant-rayon-aab.notion.site/8-bcd7811b4e404cfebd791d65f6d29372)](https://fragrant-rayon-aab.notion.site/8-bcd7811b4e404cfebd791d65f6d29372) [![S3 Badge](https://img.shields.io/badge/S3-569A31?style=flat-round&logo=amazon-aws&logoColor=white)](http://ssong.ch94.s3-website.ap-northeast-2.amazonaws.com/) 



<!--http://yongminbucket.s3-website.ap-northeast-2.amazonaws.com/ 이부분은 변경이 필요한 부분 입니다 -->

## 🔧 Technologies & Software Used

<img src="https://img.shields.io/badge/Java-007396?style=flat-round&logo=OpenJDK&logoColor=white"/>  <img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-round&logo=spring&logoColor=white"/>  <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat-round&logo=SpringSecurity&logoColor=white"/>  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-round&logo=springboot&logoColor=white"/>  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=flat-round&logo=javascript&logoColor=black">  <img src="https://img.shields.io/badge/React-61DAFB?style=flat-round&logo=react&logoColor=white"/>  <img src="https://img.shields.io/badge/Redux-764ABC?style=flat-round&logo=redux&logoColor=white"/>  <img src="https://img.shields.io/badge/Axios-5A29E4?style=flat-round&logo=axios&logoColor=white"/>  <img src="https://img.shields.io/badge/Thunk-FF81F9?style=flat-round"/>  


<img src="https://img.shields.io/badge/git-F05032?style=flat-round&logo=git&logoColor=white"/>  <img src="https://img.shields.io/badge/github-181717?style=flat-round&logo=github&logoColor=white"/> <img src="https://img.shields.io/badge/githubactions-2088FF?style=flat-round&logo=githubactions&logoColor=white"/>  <img src="https://img.shields.io/badge/JSON Web Token-000000?style=flat-round&logo=JSON Web Tokens&logoColor=white"/>  <img src="https://img.shields.io/badge/Gradle-02303A?style=flat-round&logo=Gradle&logoColor=white"/>  <img src="https://img.shields.io/badge/IntelliJIDEA-000000?style=flat-round&logo=IntelliJIDEA&logoColor=white"/>  <img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=flat&logo=Visual Studio Code&logoColor=white" />  <img src="https://img.shields.io/badge/Postman-FF6C37?style=flat-round&logo=Postman&logoColor=white"/> 

<img src="https://img.shields.io/badge/AmazonS3-569A31?style=flat-round&logo=AmazonS3&logoColor=white"/>  <img src="https://img.shields.io/badge/AmazonEC2-FF9900?style=flat-round&logo=AmazonEC2&logoColor=white"/>  <img src="https://img.shields.io/badge/AmazonRDS-527FFF?style=flat-round&logo=AmazonRDS&logoColor=white"/>  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-round&logo=MySQL&logoColor=white"/>  <img src="https://img.shields.io/badge/Ubuntu-E95420?style=flat-round&logo=Ubuntu&logoColor=white"/> <img src="https://img.shields.io/badge/FileZilla-BF0000?style=flat-round&logo=filezilla&logoColor=white"/>

 <img src="https://img.shields.io/badge/Notion-000000?style=flat-round&logo=Notion&logoColor=white"/> <img src="https://img.shields.io/badge/Slack-4A154B?style=flat-round&logo=slack&logoColor=white"/>

## 🔑 [프로젝트 구현 기능](http://yongminbucket.s3-website.ap-northeast-2.amazonaws.com) 

1. 홈 화면 
  
   모든 사용자가 전체 게시글을 조회 및 검색을 할 수 있도록 구현하였습니다.
 
   회원가입과 로그인 을 헤더에 설정하여 로그인 시 마이페이지및 본인 닉네임을 확인할 수 있도록 하였습니다.  

2. 회원가입 로그인 

   JWTWebToken + spring boot Security 를 적용하여 구현하였습니다.
   
   Kakao Login (Oauth2) 를 적용하여 구현하였습니다. 

3. 게시글 조회 

    최신순으로 조회할 수 있으며 전체 게시글, 상세 게시글, 마이페이지 게시글로 분류하였습니다. 

4. 게시글 작성 

    사용자가 입력한 데이터를 Amazon S3로 저장하여 관리합니다.
    
    작성자만 수정/ 삭제 할 수있으며 좋아요 기능이 포함되어 있습니다. 


5. 게시글 좋아요 

    사용자가 게시글에 좋아요를 누를 수 있고 다시 한 번 누르면 좋아요가 취소됩니다.

    상세 페이지의 경우 모든 사용자가 조회 가능 하지만, 로그인을 한 경우에만 좋아요를 누를 수 있습니다. 
    
6. 댓글 및 댓글 좋아요 

    게시글에 댓글을 작성할 수 있고, 작성자만 수정/ 삭제 할 수 있으며 게시글과 같이 좋아요 기능이 포함되어 있습니다.

7. 마이페이지 

    내가 작성한 글, 댓글을 확인할 수 있습니다.

    해당 게시글의 상세페이지를 확인할 수 있습니다. 

8. 예외처리 

    Custom ErrorCode 및 StatusCode를 전달하여 프론트엔드와 명확하게 소통하였습니다. 


## 🏀 [Trouble Shooting](https://www.notion.so/5-SA-f4ebf090ac43441f88ff063a6ee7cd78)

   Back-End
   1. 개인 계정마다 게시글, 댓글에 대한 좋아요 여부 판단 로직
   2. CICD 자동배포 민감한 정보 숨기기
   3. CORS 문제 해결
   4. API 명세서에 작성에 대한 중요성
  
   Front-End
  1. API 리퀘스트 변경에 대한 미숙지
  2. 로그인 시, 토큰 전송 방식으로 인한 이전 토큰 송신 문제
  3. dispatch 보낼 시 응답의 특정 조건에 따라 그 뒤 동작 달라지는 기능
  4. axios 요청 보낼 때 data-type


<!--

**Here are some ideas to get you started:**

🍿 Fun facts - what does your team eat for breakfast?
🧙 Remember, you can do mighty things with the power of [Markdown](https://docs.github.com/github/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)
-->
