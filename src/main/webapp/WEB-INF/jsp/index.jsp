<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<sec:authorize var="isLogin" access="isAuthenticated()" />
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${title}</title>
<link rel="icon" href="/img/favicon.png">
<link rel="stylesheet" href="/css/index.css?ver=12">
<link rel="stylesheet" href="/css/boardList.css?ver=5">
<link rel="stylesheet" href="/css/boardDetail.css?ver=9">
<link rel="stylesheet" href="/css/boardRegMod.css?ver=4">
<link rel="stylesheet" href="/css/login.css?ver=27">
<link rel="stylesheet" href="/css/join.css?ver=89">
<link rel="stylesheet" href="/css/test.css?ver=5">
<link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
<!-- 아웃라인 material-icon 링크 추가 -->
<link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
<!-- 위지윅 에디터 추가 -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">

<script src="//cdn.ckeditor.com/4.15.0/standard/ckeditor.js"></script>

</head>

<body>
	<div id="bg1">
		<img src="/img/yousef-salhamoud-kQ6mh2yagDw-unsplash.jpg" alt="" id="bg1_1">
	</div>
	<main class="centralContainer">
		<header class="centralHeader">
			<div class="centralLogo">
				<img id="centralLogoImage" src="/img/logowhite.png" alt="">
			</div>
			<div class="centralSearch">
				<form action="" method="POST" class="centralSearchFrm" onsubmit="findVideo(event)">
					<div class="centralSearch1_1">
						<input type="text" name="searchVideo" id="searchVideo" class="Search1_1__input" placeholder="검색">
					</div>
					<div class="centralSearch1_2">
						<span class="material-icons headMenus" onclick="findVideo(event)">search</span>
					</div>
				</form>
			</div>
			<div class="centralMenu">
				<div class="menus centralMenu1_1">
					<span class="material-icons" onclick="alertMenuInit()">notifications_none</span>
				</div>
				<div class="menus centralMenu1_2">
					<span class="material-icons" onclick="userMenuInit(${isLogin})">person_outline</span>
				</div>
			</div>
		</header>
		<section class="centralSection">
			<div class="sectionContainer">
				<div class="slideContainer"></div>
			</div>
			<aside class="centralSidebar">
				<div class="menus sidebarMenu1_1">
					<span class="material-icons" onclick="boardInit()">assignment</span>
				</div>
				<div class="menus sidebarMenu1_2">
					<span class="material-icons" onclick="chatInit()">chat</span>
				</div>
			</aside>
		</section>
	</main>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="https://embed.twitch.tv/embed/v1.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<script src='https://unpkg.com/react-player/dist/ReactPlayer.standalone.js'></script>

	<script src="/js/index.js?aaa=7"></script>
	<script src="/js/login.js"></script>
	<script src="/js/boardList.js?ver=54"></script>
	<script src="/js/boardRegMod.js?ver=45"></script>

	<script src="/js/test.js?ver=13"></script>

	<script>

		var stompClient = null;
		function chkId() {
			const username = frm.username.value
			axios.get('/IdChk', {
				params: {
					username
				}
			}).then(function (res) {
				if (res.data == '2') { //아이디 없음
					idChkResult.innerText = '사용할 수 있는 아이디입니다.'
				} else if (res.data == '3') { //아이디 중복됨
					idChkResult.innerText = '이미 사용중입니다.'
				}
			})
		}

		function connect(event) {
			username = '${UserInfo.username}';
			console.log(username)
			if (username) {
				console.log("connect Start")
				var socket = new SockJS('/ws');
				stompClient = Stomp.over(socket);

				stompClient.connect({}, onConnected, onError);
			}
		}

		function onConnected() {
			stompClient.subscribe('/subscribe/public', onMessageReceived);
			stompClient.send("/publish/chat.addUser",
				{},
				JSON.stringify({ sender: username, type: 'JOIN' })
			)
			console.log("onConnected 끝")
		}

		function onError(error) {
			connectingElement.textContent = "ConnectionError";
			connectingElement.style.color = 'red';
		}

		function sendMessage(event) {
			console.log('message전송 시작')
			var messageContent = inputChat.value.trim();
			console.log('messageContent: ' + messageContent)
			if (messageContent && stompClient) {
				var chatMessage = {
					sender: username,
					content: inputChat.value,
					type: 'CHAT'
				};
				stompClient.send("/publish/chat.sendMessage", {}, JSON.stringify(chatMessage));
				inputChat.value = '';
			}
			event.preventDefault();
		}

		function onMessageReceived(payload) {
			var message = JSON.parse(payload.body);

			var messageElement = document.createElement('li');
			console.log('message.type:' + message.type)
			console.log('message.sender: ' + message.sender)
			if (message.type === "JOIN") {
				message.content = message.sender + "joined!";
			} else if (message.type === "LEVAE") {
				message.content = message.sender + "left!";
			} else {
				/* 채팅창에 채팅 메세지 띄우기 */
				messageElement.className = 'chat-message';
				inputUl.append(messageElement);

				var usernameElement = document.createElement('span');
				var usernameText = document.createTextNode(message.sender);
				usernameElement.appendChild(usernameText);
				messageElement.appendChild(usernameElement);
			}
			var textElement = document.createElement('p');
			var messageText = document.createTextNode(message.content);
			textElement.appendChild(messageText);
			inputUl.append(messageText)

			messageElement.appendChild(textElement);

		}
	</script>

	<script>
		const underline = document.getElementsByClassName('tabMenuBtn')

		function temp(idx) {
			openMyPageDetails(idx)
			addUnderLine(idx)
			//removeUnderLine(idx)
		}

		function addUnderLine(idx) {
			if (document.querySelector('.underline')) {
				const under_element = document.querySelector('.underline')
				under_element.classList.remove('underline')
			}
			underline[idx].classList.add('underline')
		}

		function openMyPageDetails(idx) {
			const tabBoxContainer = document.querySelector('.tabBoxContainer');

			if (idx == 4) {
				location.href = 'http://localhost:8080/logout';
				return;
			}

			// jsp 파일 이름이 바뀌면 controller와 pageName이 변경되어야 한다.
			const pageName = '/userinfo' + idx;
			fetch(pageName).then(function (response) {
				response.text().then(function (text) {
					tabBoxContainer.innerHTML = text;
				})
			})
		}

	</script>
	<script>
		function profileAddIconChange() {

			const personIcon = document.querySelector('.personIcon');
			const addIcon = document.querySelector('.addIconHidden');
			personIcon.style.display = 'none';
			personIcon.style.transition = '0.5s';
			addIcon.classList.remove('addIconHidden');
			const addIcon2 = document.querySelector('.addIcon');
			addIcon2.style.color = 'red';
		}

		function profilePersonIconChange() {
			const addIcon = document.querySelector('.addIcon');
			addIcon.classList.add('addIconHidden');
			const personIcon = document.querySelector('.personIcon');
			personIcon.style.removeProperty('display');
		}
		function fileNameMove() {
			const fileName = document.getElementById('fileName');
			const profile = document.getElementById('profile');
			fileName.value = profile.value;

		}
	</script>
	<sec:authorize access="isAuthenticated()">
		<c:if test="${userInfo != null}">

			<script>
				console.log('userInfo= ${userInfo}')
			</script>

		</c:if>

		<c:if test="${userDomain != null}">
			<script>
				console.log('userDomain = ${userDomain}')
			</script>
			<c:forEach items="${userDomain.sns}" var="item">
				<c:if test="${item == 'twitch'}">
					<script type="text/javascript">

						axios.get('/getStreams', {}).then(function (res) {
							res.data.data.forEach(function (item) {
								loadTwitchFollowSwiper(item)
							})
							reloadPagination();
						}).then(function () {
							const playBtn = document.querySelectorAll(".playBtn");
							for (var i = 0; i < playBtn.length; i++) {
								playBtn[i].setAttribute("onclick", "videoLoad()");
							}
						})
						function videoLoad() {
							const videoLink = document.querySelector(".swiper-slide-active>.thumbnail");
							const thumbnails = document.getElementsByClassName("thumbnail");
							for (var i = 0; i < thumbnails.length; i++) {
								thumbnails[i].id = "";
							}
							videoLink.id = "video-embed";
							const url = videoLink.getAttribute("streamerURL");
							const videoURL = "https://www.twitch.tv/" + url.substring(url.indexOf("user_") + 5, url.lastIndexOf("-1920x"));
							console.log(videoURL);

							reactPlayer(videoURL);
						}
						function reactPlayer(url) {
							const container = document.getElementById('video-embed')
							console.log(container)
							renderReactPlayer(container, {
								url,
								playing: true,
								controls: false,
								width: "100%",
								height: "100%"
							})
						}
					</script>
				</c:if>
			</c:forEach>
		</c:if>
	</sec:authorize>
</body>

</html>