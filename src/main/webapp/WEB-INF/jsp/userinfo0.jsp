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
		<div class="userProfileHeader"><h3>프로필 사진</h3></div>
		<div class="myPageBox">
			<div class="userNickName">${userInfo.nickname }님</div>
			<div>
				<label for="profile">프로필  사진 추가</label>
				<input type="file" name="profile" vlaue="">
			</div>
			<c:choose>
				<c:when test="${userInfo.profileImg != null}">
					<img clss= "pImg" src="/img/user/${userInfo.userseq}/${userInfo.profileImg}" alt="사용자지정 프로필">
				</c:when>
				<c:otherwise>
					<img class="pImg" src="/img/default_profile.jpg" alt="기본프로필">
				</c:otherwise>
			</c:choose>	
		</div>
	</div>
</div>
</body>
</html>