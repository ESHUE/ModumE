<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/css/userinfo.css">

<div style="background:white">
	<div>
		<div class="sns-modume-title">기본 정보</div>
		<form action="">
			<div class="personalInformatin">
				<span class="material-icons privacyIcon">person</span>
				<input type="text" name="nickname" placeholder="이름" id="modifyName" value="">
			</div>
		</form>
	</div>
	<div class="">
		<div class="sns-modume-title">계정추가</div>
		<div class="sns-modume-sign">
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
	</div>	
</div>