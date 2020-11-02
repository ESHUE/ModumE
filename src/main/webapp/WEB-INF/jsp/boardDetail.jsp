<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="board-btns board-detail-btn">
	<!-- 뒤로가기 버튼 -->
	<div class="go-to-back board-detail-back cursor circle-btn50" onclick="changeLocation('/boardList')">
		<span class="material-icons">arrow_back</span>
	</div>
    <!-- top 버튼 -->
    <div class="go-to-top board-detail-top cursor circle-btn50" onclick="goToTop()">
        <span class="material-icons">arrow_upward</span>
    </div>
</div>

<div class="board-detail boardContents">
	<c:if test="${loginUser != null }">
		<c:if test="${boardDetail.userseq.userseq == loginUser.userseq}">
			<div class="board-detail-modDel__btns flex-center">
				<div class="rectangle-small-btn mr5" onclick="goToEditor('${boardDetail.title }')">수정</div>
				<div class="rectangle-small-btn">삭제</div>
			</div>
		</c:if>
	</c:if>
    <table class="board-detail-table board-table">
        <tr class="board-detail-profile">
            <td class="board-profile-td">
                <div class="board-profile__box">
                	<c:if test="${boardDetail.userseq.profileImg == null }">
		                <img class="board-profile__img" src="/img/test-profile.jpg" alt="프로필">
	            	</c:if>
	            	<c:if test="${boardDetail.userseq.profileImg != null }">
		                <img class="board-profile__img" src="/img/${boardDetail.userseq.profileImg }" alt="프로필">
	            	</c:if>
                </div>
            </td>
            <td class="board-user-name">${boardDetail.userseq.nickname }</td>
        </tr>
        <tr class="board-title">
            <td class="board-detail-title" colspan="2">${boardDetail.title }</td>
        </tr>
        <tr class="board-date">
            <td class="board-detail-date" colspan="2">─── 작성 : 
            	<fmt:formatDate value="${boardDetail.rdate }" pattern="MM-dd"/>
           		<c:if test="${boardDetail.rdate != boardDetail.mdate }">
		            │ 수정 : 
		            <fmt:formatDate value="${boardDetail.mdate }" pattern="MM-dd"/> 
	            </c:if>
            ───</td>
        </tr>
        <tr class="board-ctnt board-detail-ctnt">
            <td class="board-detail-ctnt" colspan="2">${boardDetail.content }</td>
        </tr>
    </table>
</div>
<div class="board-detail-option-box board-icons">
    <div class="board-hits cursor flex-center">
        <span class="material-icons-outlined mr5">remove_red_eye</span>
        <span>${boardDetail.hits }</span>
    </div>
    <div class="board-comment cursor flex-center">
        <span class="material-icons-outlined mr5">comment</span>
        <span>10</span>
    </div>
    <div class="board-like cursor flex-center">
        <span class="material-icons-outlined mr5">thumb_up</span>
        <span>10</span>
    </div>
</div>