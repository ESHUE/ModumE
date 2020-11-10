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
<link rel="stylesheet" href="/css/index.css?ver=9544">
<link rel="stylesheet" href="/css/boardList.css?ver=3">
<link rel="stylesheet" href="/css/boardDetail.css?ver=4">
<link rel="stylesheet" href="/css/boardRegMod.css?ver=1">
<link rel="stylesheet" href="/css/comment.css?ver=8">
<link rel="stylesheet" href="/css/login.css?ver=27">
<link rel="stylesheet" href="/css/join.css?ver=89">
<link rel="stylesheet" href="/css/test.css?ver=5">

<link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">

<!-- 아웃라인 material-icon 링크 추가 -->
<link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">

<script src="//cdn.ckeditor.com/4.15.0/standard/ckeditor.js"></script>
</head>

<body>
	<div id="bg1">
		<img src="/img/yousef-salhamoud-kQ6mh2yagDw-unsplash.jpg" alt="" id="bg1_1">
	</div>
	<main class="centralContainer">
		<header class="centralHeader">
			<div class="centralLogo cursor" onclick="location.href='/main'">
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
					<span class="material-icons" onclick="userMenuInit(${isLogin},'${userInfo.nickname }')">person_outline</span>
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

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="https://embed.twitch.tv/embed/v1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src='https://unpkg.com/react-player/dist/ReactPlayer.standalone.js'></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

	<script src="/js/index.js?aaa=75092"></script>
	<script src="/js/search.js?aa=afasef46"></script>
	<script src="/js/test.js?ver=13"></script>
	<script src="/js/boardRegMod.js?ver=45"></script>
	<script src="/js/boardList.js?ver=80"></script>
	<script src="/js/boardDetail.js?ver=1"></script>
	<script src="/js/join.js?ver=23"></script>
	<script src="/js/comment.js?ver=3"></script>
	<!-- 트위치 채널 긁어오기(채널지정) -->

	<script>
	
	
	/* Profile  Eunjeong Start */
	
	/* Eunjeong End */
	</script>

	<sec:authorize access="isAuthenticated()">
	
		<c:if test="${userInfo != null}">

			<script>
				//console.log('userInfo= ${userInfo}')
			</script>
		</c:if>
		
		<c:if test="${userDomain != null}">
			<script>
				//console.log('userDomain = ${userDomain}')
			</script>
			<c:forEach items="${userDomain.sns}" var="item">
				<c:if test="${item == 'twitch'}">
				<script>
				/* member = '${member}'; */	
				</script>
					<script type="text/javascript" src="/js/twitch.js">
					</script>
				</c:if>
			</c:forEach>
		</c:if>
	</sec:authorize>
</body>

</html>