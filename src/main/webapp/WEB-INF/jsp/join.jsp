<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="StringUtils"
	class="com.amolrang.modume.utils.StringUtils" />
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
			<form id="frm" class="frm" action="/join" runat="server" enctype="multipart/form-data" method="post">
				<div class="profileSelect">
					<label for="profile" id="profileIcon">
						<span class="material-icons personIcon" onmouseover="profileAddIconChange()" onmouseout="profilePersonIconChange()">person</span>
            			<span class="material-icons addIconHidden addIcon">add</span> 
            			<input type="text" id="fileName"  name="fileName" readonly="readonly">         			
					</label>
					<!-- <img alt="userProfile" src="#" id="imgPreview"> -->
					<div id="image_container"></div>  
					<input type="file" name="profile" id="profile" accept="image/*" onchange="profileInfo(event)"> 
					
				</div>
				<div id="idChkResult" class="msg"></div>
				<div class="joinListContainer">
					<input type="text" name="username" placeholder="아이디" value="abc111">
					<button type="button" onclick="chkId()">아이디 중복체크</button>
				</div>
				<div class="joinListContainer">
					<input type="password" name="password" placeholder="비밀번호" value="123!456!">
				</div>
				<div class="joinListContainer">
					<input type="password" name="password_re" placeholder="비밀번호 확인" value="123!456!">
				</div>
				<div class="joinListContainer">
					<input type="text" name="nickname" placeholder="이름" value="은찌리">
				</div>
				<div class="joinSbm">
					<input type="submit" value="sign up">

				</div>
			</form>
			<div class="retrunToPage">
				<span onclick="moveToLogin()" class="returnToLoginPage">로그인</span> 
				<a href="/" class="returnToMainPage">메인화면</a>
			</div>
		</div>
	</div>
</body>

</html>