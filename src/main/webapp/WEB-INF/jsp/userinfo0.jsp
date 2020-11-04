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
			<c:choose>
				<c:when test="${userInfo.profileImg != null}">
					<img clss= "pImg" src="/img/user/${userInfo.userseq}/${userInfo.profileImg}" >
				</c:when>
				<c:otherwise>
					<img class="pImg" src="/img/default_profile.jpg" alt="기본프로필">
				</c:otherwise>
			</c:choose>	
			<div class="userProfileHeader"><span class="userNickName">${userInfo.nickname }</span>님 프로필 사진 설정</div>
			<div>
				<label for="profile"></label>
				<input type="file" name="profile" vlaue="">
			</div>
		</div>
	</div>
</div>
</body>
</html>