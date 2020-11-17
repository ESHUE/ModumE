# 방송 시청 플랫폼, 모두미(MODUME)
### 개요
팀 이슈의 첫 번째 작품인 MODUME는 SPRING 기반의 방송 시청 플랫폼입니다. 여러 아이디를 통합하여 자신이 원하는 플랫폼의 방송과 영상을 시청할 수 있도록 제작했습니다.

### 예시 영상

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
[BACKEND] 
전체 구성은 MVC 생성 패턴을 통한 웹 통신 
Spring Security를 통한 웹페이지 접근 제어 및 로그인 구성 
OAUTH2를 로그인을 통해 인증받은 Access Token으로 API를 호출하여 목표하는 코어 기능을 구현 
Hibernate 메소드를 활용하여 Database 내부 테이블 및 쿼리를 자동으로 생성 
WEBSOCKET을 활용하여 타 사용자와의 메세지 교환 기능 구성 

[FRONTEND] 
JSP 템플릿 엔진, Ajax를 적극 활용한 비동기식 웹페이지를 구성 
JavaScript를 활용하여 동적 메뉴를 구성, 웹페이지의 이동을 최소화
