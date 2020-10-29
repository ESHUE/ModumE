<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="board-btns">
       <!-- top 버튼 -->
    <div class="go-to-top cursor circle-btn50" onclick="goToTop()">
        <span class="material-icons">arrow_upward</span>
    </div>
    <!-- 새 글 버튼-->
    <div class="new-post cursor circle-btn50" onclick="goToEditor()">
        <span class="material-icons">post_add</span>
    </div>
</div>
<div class="boardContents">
	<c:if test="${list == null }">
		<div> 글을 등록해주세요 </div>
	</c:if>
	<c:forEach items="${list}" var="item">
	    <table class="board-table cursor" onclick="goToDetail(${item.USERBOARD_SEQ})">
	    <tr class="board-profile">
	        <td rowspan="2" class="board-profile-td">
	            <div class="board-profile__box">
	            	<c:if test="${item.user.profileImg == null }">
		                <img class="board-profile__img" src="/img/test-profile.jpg" alt="프로필">
	            	</c:if>
	            	<c:if test="${item.user.profileImg != null }">
		                <img class="board-profile__img" src="/img/${item.user.profileImg }" alt="프로필">
	            	</c:if>
	            </div>
	        </td>
	        <td class="board-user-name" colspan="2">${item.user.nickname }</td>
	    </tr>
	    <tr class="board-date">
	        <td colspan="2">${item.r_date}</td>
	    </tr>
	    <tr class="board-title">
	        <td colspan="3">${item.title}</td>
	    </tr>
	    <tr class="board-ctnt">
	        <td colspan="3">${item.content}</td>
	    </tr>
	    <!-- c:if 이미지 개수가 1개인 경우와 2개 이상인 경우 구분 -->
	    <!-- c:if 이미지 개수가 1개 이상일 경우, tr 안에는 for each로 처리, img는 두개까지만 -->
	    
		<!-- 여기 이미지 관련 포문 넣기!!! -->



	    <tr class="board-foot">
	        <td colspan="3">
	            <div class="board-icons">
	                <div class="board-hits flex-center">
	                    <span class="material-icons-outlined mr5">remove_red_eye</span>
	                    <span>${item.hits }</span>
	                </div>
	                <div class="board-comment flex-center">
	                    <span class="material-icons-outlined mr5">comment</span>
	                    <span>10</span>
	                </div>
	                <div class="board-like flex-center">
	                    <span class="material-icons-outlined mr5">thumb_up</span>
	                    <span>10</span>
	                </div>
	            </div>
	        </td>
	    </tr>
	</table>
</c:forEach>
	 <!-- 1번 테이블 -->
    <table class="board-table cursor" onclick="goToDetail(${item.USERBOARD_SEQ})">
        <tr class="board-profile">
            <td rowspan="2" class="board-profile-td">
                <div class="board-profile__box">
                    <img class="board-profile__img" src="/img/test-profile.jpg" alt="프로필">
                </div>
            </td>
            <td class="board-user-name" colspan="2">닉네임</td>
        </tr>
        <tr class="board-date">
            <td colspan="2">10-20</td>
        </tr>
        <tr class="board-title">
            <td colspan="3">글제목입니다람쥐</td>
        </tr>
        <tr class="board-ctnt">
            <td colspan="3">
                동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세
            </td>
        </tr>
        <!-- c:if 이미지 개수가 1개인 경우와 2개 이상인 경우 구분 -->
        <!-- c:if 이미지 개수가 1개 이상일 경우, tr 안에는 for each로 처리, img는 두개까지만 -->
        <tr class="board-ctnt__imgs">
            <td colspan="3">
                <div class="board-ctnt-img__box2">
                    <img class="board-ctnt__img" src="/img/test-img.jpg" alt="">
                </div>
                <!-- 이미지 개수가 3개 이상인 경우, 2번째 이미지에 c:if 처리  -->
                <div class="board-ctnt-img__box2">
                    <div class="remain-img flex-center">+3</div>
                    <img class="board-ctnt__img board-ctnt__img2" src="/img/test-img.jpg" alt="">
                </div>
            </td>
        </tr>
        <tr class="board-foot">
            <td colspan="3">
                <div class="board-icons">
                    <div class="board-hits flex-center">
                        <span class="material-icons-outlined mr5">remove_red_eye</span>
                        <span>10</span>
                    </div>
                    <div class="board-comment flex-center">
                        <span class="material-icons-outlined mr5">comment</span>
                        <span>10</span>
                    </div>
                    <div class="board-like flex-center">
                        <span class="material-icons-outlined mr5">thumb_up</span>
                        <span>10</span>
                    </div>
                </div>
            </td>
        </tr>
    </table>
    
    <!-- 2번 테이블 -->
    <table class="board-table cursor" onclick="goToDetail(${item.USERBOARD_SEQ})">
        <tr class="board-profile">
            <td rowspan="2" class="board-profile-td">
                <div class="board-profile__box">
                    <img class="board-profile__img" src="/img/test-profile.jpg" alt="프로필">
                </div>
            </td>
            <td class="board-user-name" colspan="2">닉네임</td>
        </tr>
        <tr class="board-date">
            <td colspan="2">10-20</td>
        </tr>
        <tr class="board-title">
            <td colspan="3">글제목입니다람쥐</td>
        </tr>
        <tr class="board-ctnt">
            <td colspan="3">
                동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세
            </td>
        </tr>
        <!-- c:if 이미지 개수가 1개인 경우와 2개 이상인 경우 구분 -->
        <!-- c:if 이미지 개수가 2개 이상일 경우, tr 안에는 for each로 처리, img는 두개까지만 -->
        <tr class="board-ctnt__imgs">
            <td colspan="3">
                <div class="board-ctnt-img__box1">
                    <img class="board-ctnt__img" src="/img/test-img.jpg" alt="">
                </div>
            </td>
        </tr>
        <tr class="board-foot">
            <td colspan="3">
                <div class="board-icons">
                    <div class="board-hits flex-center">
                        <span class="material-icons-outlined mr5">remove_red_eye</span>
                        <span>10</span>
                    </div>
                    <div class="board-comment flex-center">
                        <span class="material-icons-outlined mr5">comment</span>
                        <span>10</span>
                    </div>
                    <div class="board-like flex-center">
                        <span class="material-icons-outlined mr5">thumb_up</span>
                        <span>10</span>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>