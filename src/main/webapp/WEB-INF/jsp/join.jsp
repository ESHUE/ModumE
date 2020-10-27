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
			<div class="joinHeader">
				<h1>create account</h1>
			</div>
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
					<label for="profile">파일 첨부</label> 
					<input type="file" name="profile">
				</div>
				<div class="joinSbm">
					<input type="submit" value="회원가입">
				</div>
			</form>
			<div>
				<a href="/">메인화면</a>
			</div>
		</div>

		<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
		<script>
		function chkId() {
			const id = frm.id.value
			axios.post('/IdChk', {id}).then(function(res) {
				if(res.data == '2') { //아이디 없음
					idChkResult.innerText = '사용할 수 있는 아이디입니다.'
				} else if(res.data == '3') { //아이디 중복됨
					idChkResult.innerText = '이미 사용중입니다.'
				}
			})
			
		}
	</script>
	</div>
	</form>
</body>
</html>