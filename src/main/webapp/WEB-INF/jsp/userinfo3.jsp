<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/userInfo.css">
</head>
<body>
	<div>
		<div class="input-container focus kakaoLogin">
			<a href="/oauth2/authorization/kakao">Kakao</a>
		</div>
		<div class="input-container focus googleLogin">
			<a href="/oauth2/authorization/google"><img src="/img/google_logo.svg" alt="google_logo"></a>
		</div>
		<div class="input-container focus twitchLogin">
			<a href="/oauth2/authorization/twitch"><img src="https://blog.kakaocdn.net/dn/0WCc3/btqD2dnMsO9/5eYc5RKNjG5RAz2gnelcuK/img.jpg"></a>
		</div>
		<div class="input-container focus naverLogin">
			<a href="/oauth2/authorization/naver">Naver</a>
		</div>
	</div>
</body>
</html>