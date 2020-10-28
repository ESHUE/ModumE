<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="StringUtils"
	class="com.amolrang.modume.utils.StringUtils" />
<link rel="stylesheet" type="text/css" href="/css/join.css">
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>
	<div id="sectionContainerCenter">
		<div>
			<div class="joinHeader"><h1>create account</h1></div>
			<div class="msg">${err}</div>
			<form id="frm" class="frm" action="/join" enctype="multipart/form-data" method="post">
				<div id="idChkResult" class="msg"></div>
				<div class="joinListContainer">
					<input type="text" name="username" placeholder="아이디">
					<button type="button" onclick="chkId()">아이디 중복체크</button>
				</div>
				<div class="joinListContainer">
					<input type="password" name="password" placeholder="비밀번호">
				</div>
				<div class="joinListContainer">
					<input type="password" name="password_re" placeholder="비밀번호 확인">
				</div>
				<div class="joinListContainer">
					<input type="text" name="nickname" placeholder="이름">
				</div>
				<div class="profileSelect">
					<label for="profile">프로필 이미지</label> 
  					<input type="file" name="profile" id="profile"> 
				</div>
				<div class="joinSbm">
					<input type="submit" value="sign up">

				</div>
			</form>
			<div class="moveList">
				<span onclick="moveToLogin()">로그인</span> 
				<a href="/">메인화면</a>
			</div>
		</div>
	</div>
</body>
</html>