<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/css/userInfo.css?ver=4546546">

<div class="userInfo3Container">
	<div>
		<div class="userInfoSection1">
			<div class="userInfo3_1">기본 정보</div>
			<form action="">
				<div class="personalInformatin">
					<span class="material-icons privacyIcon">person</span>
					<input type="text" name="nickname" placeholder="이름" id="modifyName" value="">
				</div>
			</form>
		</div>
		<div class="userInfoSection2">
			<div class="userInfo3_1">계정 연동</div>
			<div class="sns-modume-sign2">
				<div class=" focus kakaoLogin userInfo3">
					<a href="/oauth2/authorization/kakao">Kakao</a>
				</div>
				<div class=" focus googleLogin userInfo3">
					<a href="/oauth2/authorization/google"><img src="/img/google_logo.svg" alt="google_logo"></a>
				</div>
				<div class=" focus twitchLogin userInfo3">
					<a href="/oauth2/authorization/twitch"><img src="https://blog.kakaocdn.net/dn/0WCc3/btqD2dnMsO9/5eYc5RKNjG5RAz2gnelcuK/img.jpg"></a>
				</div>
				<div class=" focus naverLogin userInfo3">
					<a href="/oauth2/authorization/naver">Naver</a>
				</div>
			</div>
		</div>
	</div>	
</div>