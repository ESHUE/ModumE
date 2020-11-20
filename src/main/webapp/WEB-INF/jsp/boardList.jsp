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
	<c:if test="${fn:length(list) == 0 }">
		<div> 글을 등록해주세요 </div>
	</c:if>
	<c:forEach items="${list}" var="item">
	    <table class="board-list-table board-table cursor" onclick="goToDetail(${item.boardseq})">
	    <tr class="board-profile">
	        <td rowspan="2" class="board-profile-td">
	            <div class="board-profile__box flex-center">
	            	<c:if test="${item.userseq.profileImg == null }">
		                <img class="board-profile__img" src="/img/test-profile.jpg" alt="프로필">
	            	</c:if>
	            	<c:if test="${item.userseq.profileImg != null }">
		                <img class="board-profile__img" src="/upload/${item.userseq.profileImg }" alt="프로필">
	            	</c:if>
	            </div>
	        </td>
	        <td class="board-user-name" colspan="2">${item.userseq.nickname }</td>
	    </tr>
	    <tr class="board-date">
	    	<td colspan="2">
	    		<fmt:formatDate value="${item.rdate}" pattern="MM-dd"/>
	        </td>
	    </tr>
	    <tr class="board-title">
	        <td colspan="3">${item.title}</td>
	    </tr>
	    <tr class="board-ctnt">
	        <td colspan="3">${item.convertcontent}</td>
	    </tr>

		<tr class="board-ctnt__imgs">
	        <td colspan="3">
                <c:forEach items="${item.imgList}" var="img" begin="0" end="1" varStatus="status">
	            <c:if test="${fn:length(item.imgList) == 1}">
	                <div class="board-ctnt-img__box1">
	                    <img class="board-ctnt__img" src=${img.imgpath } alt="">
	                </div>
	            </c:if>
	            <c:if test="${fn:length(item.imgList) > 1}">
	            	<c:if test="${status.index == 0 }">
	                    <div class="board-ctnt-img__box2">
	                        <img class="board-ctnt__img" src="${img.imgpath}" alt="">
	                    </div>
                    </c:if>
                    <c:if test="${status.index == 1 }">
                        <div class="board-ctnt-img__box2">
                        	<c:if test="${fn:length(item.imgList) > 2 }">
                            	<div class="remain-img flex-center">+${fn:length(item.imgList) - 2 }</div>
                            </c:if>
                            <img class="board-ctnt__img board-ctnt__img2" src="${img.imgpath}" alt="">
                        </div>
                    </c:if>
                </c:if>
                </c:forEach>
	        </td>
	    </tr>

	    <tr class="board-foot">
	        <td colspan="3">
	            <div class="board-icons">
	                <div class="board-hits flex-center">
	                    <span class="material-icons-outlined mr5">remove_red_eye</span>
	                    <span>${item.hits }</span>
	                </div>
	                <div class="board-comment flex-center">
	                    <span class="material-icons-outlined mr5">comment</span>
	                    <span>${item.comment_cnt }</span>
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
	 
</div>