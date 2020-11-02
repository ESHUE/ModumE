<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/userInfo.css">
</head>
<body>
<div id="userProfileContainer">
	<div class="userProfileHeader"><h3>프로필 사진</h3></div>
	<div class="userNickName">${userInfo.nickname }님</div>
	<div>
		<label for="profile2">프로필  사진 추가</label>
		<input type="file" name="profile2" vlaue="">
	</div>
</div>
</body>
</html>