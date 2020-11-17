# 방송 시청 플랫폼, 모두미(MODUME)
### 개요
팀 이슈의 첫 번째 작품인 MODUME는 SPRING 기반의 방송 시청 플랫폼입니다. 여러 아이디를 통합하여 자신이 원하는 플랫폼의 방송과 영상을 시청할 수 있도록 제작했습니다.

### 예시 영상
■ <b>회원가입</b> </br>
<img src="https://postfiles.pstatic.net/MjAyMDExMTdfOTMg/MDAxNjA1NTk2NjEwOTI2.ZN6HzN2XSTfKXuQ_zqmP_mrVeTXXsZpDYLy0l_6e3uog.mBWJnIZOahfM0FestWpNbHPd5IJTQNoL_rbGPxtUL9kg.GIF.hyojin4588/1.gif?type=w966"> </br>
</br>
■ <b>동영상 검색</b> </br>
<img src="https://postfiles.pstatic.net/MjAyMDExMTdfMjk3/MDAxNjA1NTk2NjE1MDc2.WqudnTYFel1UjiJ-j-N92CJqe7vwosXWrAdojP5ybYEg.BZYezIKfS1Tw5w7w9eFOdqkC6ZzXIxjRsYNEu3umSSMg.GIF.hyojin4588/2.gif?type=w966" alt="이미지 준비중"> </br>
</br>
■ <b>생방송 리스트 표시</b> </br>
<img src="https://postfiles.pstatic.net/MjAyMDExMTdfMjY3/MDAxNjA1NjAwNTQxNzQz._x9-sKxEFqyouRu2_IbWY777W3_q2_0HI3USSpBKyCgg.EufhHq2jX5I1PojBxK-10O4s7QUeBhPNhmCgzu7DbGQg.GIF.hyojin4588/5.gif?type=w966" alt="이미지 준비중"> </br>
</br>
■ <b>MODUME 채팅방</b> </br>
<img src="https://postfiles.pstatic.net/MjAyMDExMTdfMjM4/MDAxNjA1NTk5Mzk3MjU4.DmE4eO4mQXp8h2Kocmou9ZEN7tC1wHHX84K4_Ml_4WUg.7Zbhi8QnnSzUxs39uo_CZsgr6TQng96vduHLX1fvEv0g.GIF.hyojin4588/3.gif?type=w966" alt="이미지 준비중"> </br>
</br>
■ <b>MODUME 게시판</b> </br>
<img src="https://postfiles.pstatic.net/MjAyMDExMTdfMTM5/MDAxNjA1NTk5NzI0OTYw.rC1wBIrX3zorHVpHZ972MSOd0cVhLBCd8wS91n7yEmMg.ZyhU1zqWk9_ErNq7I7B4ldZtvdAWgsjWTK1_TKglffkg.GIF.hyojin4588/3.gif?type=w966" alt="이미지 준비중"> </br>
</br>

### 사용된 의존성
◆ Spring Security</br>
◆ Spring DATA JPA</br>
◆ Spring Dev Tools</br>
◆ Spring Web</br>
◆ Mybatis Framework</br>
◆ Lombok</br>
◆ MySQL Driver</br>
◆ WebSocket</br>

### 프로세스
<b>[BACKEND]</b> </br>
전체 구성은 MVC 생성 패턴을 통한 웹 통신 </br>
Spring Security를 통한 웹페이지 접근 제어 및 로그인 구성 </br>
OAUTH2를 로그인을 통해 인증받은 Access Token으로 API를 호출하여 목표하는 코어 기능을 구현 </br>
Hibernate 메소드를 활용하여 Database 내부 테이블 및 쿼리를 자동으로 생성 </br>
WEBSOCKET을 활용하여 타 사용자와의 메세지 교환 기능 구성 </br>

<b>[FRONTEND]</b> </br>
JSP 템플릿 엔진, Ajax를 적극 활용한 비동기식 웹페이지를 구성 </br>
JavaScript를 활용하여 동적 메뉴를 구성, 웹페이지의 이동을 최소화 </br>
