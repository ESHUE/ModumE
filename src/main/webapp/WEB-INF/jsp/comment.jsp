<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <!-- 테이블 시작!!!! -->
    <c:forEach items="${commentList}" var="item" varStatus="status">
	    <table class="comment-table board-table">
	        <tr class="board-detail-profile">
	            <td class="board-profile-td">
	                <div class="board-profile__box">
		                <c:if test="${item.userseq.profileImg == null }">
			                <img class="board-profile__img" src="/img/test-profile.jpg" alt="프로필">
		            	</c:if>
		            	<c:if test="${item.userseq.profileImg != null }">
			                <img class="board-profile__img" src="/upload/${item.userseq.profileImg }" alt="프로필">
		            	</c:if>
	                </div>
	            </td>
	            <td class="board-user-name">
	            	<span>${item.userseq.nickname }</span>
					<span>
						<fmt:formatDate value="${item.rdate}" pattern="MM-dd"/>
					</span>
	            </td>
	            <td class="comment-modDel-td">
	            	<c:if test="${item.userseq.userseq == loginUser.userseq }">
		                <button id="comment-modDel-td${status.index }" class="material-icons cursor"
		                    onclick="showModDelBox(this, 'comment_td${status.index}', ${boardseq}, ${item.commentseq })">more_horiz</button>
	            	</c:if>
	            </td>
	        </tr>
	        <tr>
	            <td></td>
	            <td id="comment_td${status.index }" colspan="2">
	            	${item.commentcontent }
	            </td>
	        </tr>
	        <tr>
	            <td colspan="3">
	                <div class="comment-like flex-center">
	                    <span class="material-icons-outlined mr5">thumb_up</span>
	                    <span>100</span>
	                </div>
	            </td>
	        </tr>
	    </table>
    </c:forEach>
