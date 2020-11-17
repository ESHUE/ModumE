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
	<div class="profileBox">
		
		<div class="myPageBox">
			<div class="userProfileHeader"><span class="userNickName">${member.username}</span>님 프로필 사진 설정</div>
			<div class="">
				<p>서비스 준비중입니다 ( _ _ )</p>
			</div>
			<div>
				<label for="profile"></label>
				<div id="image_container"></div>  
				<input type="file" name="profile"  accept="image/*" onchange="profileInfo(event)">
			</div>
			
		</div>
	</div>
</div>
</body>
</html>