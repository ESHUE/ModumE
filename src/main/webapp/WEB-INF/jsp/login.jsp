<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="StringUtils" class="com.amolrang.modume.utils.StringUtils" />
<div id="container">
	<form id="frm" action="/login" method="post">
		<fieldset class="fld-login">
			<legend class="legend">login</legend>
			<div class="input-container focus">
				<label class="move active" for="user_id"></label><input type="text" class="no-border active " id="user_id" name="user_id" placeholder="ID" options="{ updateOn: 'keyup blur' }" required>
			</div>
			<div class="input-container focus">
				<label class="move active" for="password"></label><input type="password" class="no-border active" id="password" name="password" placeholder="PASSWORD" autocomplete="off"
					options="{ updateOn: 'keyup blur' }" required>
			</div>
		</fieldset>
		<input class="sbm-login" type="submit" value="sign in"> <input name="${_csrf.parameterName}" type="hidden" value="${_crsf.token}">
	</form>
	<div class="sbm-join">
		<a href="/join">sign up</a>
	</div>
	<div>
		<div class="input-container focus googleLogin">
			<a href="/oauth2/authorization/google"><img src="/img/google_logo.svg" alt="google_logo"></a>
		</div>
		<div class="input-container focus kakaoLogin">
			<a href="/oauth2/authorization/kakao">Kakao</a>
		</div>
		<div class="input-container focus naverLogin">
			<a href="/oauth2/authorization/naver">Naver</a>
		</div>
		<div class="input-container focus twitchLogin">
			<a href="/oauth2/authorization/twitch"><img src="https://blog.kakaocdn.net/dn/0WCc3/btqD2dnMsO9/5eYc5RKNjG5RAz2gnelcuK/img.jpg"></a>
		</div>
		
	</div>
</div>