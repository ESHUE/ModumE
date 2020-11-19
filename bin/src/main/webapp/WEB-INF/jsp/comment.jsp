<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="comment-menu">
    <div class="comment-menu1">
        <div id="allComment" class="cursor bold" onclick="changeByWriter(true); showComment(${boardseq}, this)">전체</div>
        <div id="myComment" class="cursor" onclick="changeByWriter(false); showComment(${boardseq}, this)">내 댓글</div>
    </div>
    <div class="comment-menu2">
        <div id="showASC" class="cursor bold" onclick="changeorderBy(true); showComment(${boardseq}, this)">오름차순</div>
        <div id="showDESC" class="cursor" onclick="changeorderBy(false); showComment(${boardseq}, this)">내림차순</div>
    </div>
</div>

<div class="commentContents boardContents">
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
			                <img class="board-profile__img" src="/img/${item.userseq.profileImg }" alt="프로필">
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
</div>
<form id="commentIns-form" class="comment-option-box flex-center">
    <div class="board-btns comment-backTop-btns">
        <!-- 뒤로가기 버튼 -->
        <div class="go-to-back board-detail-back cursor circle-btn50" onclick="goToDetail(${boardseq})">
            <span class="material-icons">arrow_back</span>
        </div>
        <!-- top 버튼 -->
        <div class="go-to-top board-detail-top cursor circle-btn50" onclick="goToTop()">
            <span class="material-icons">arrow_upward</span>
        </div>
    </div>
    <div class="comment-text-div">
        <textarea name="comment" class="commentIns" onkeyup="cutComment(this)" placeholder="댓글을 입력해주세요."></textarea>
    </div>
    <input type="hidden" name="commentseq" value="0">
    <div class="submitCancle-btns">
        <div onclick="formSubmit(${boardseq})" class="rectangle-small-btn commentIns-btn">
	        등록
	    </div>
	    <div onclick="cancleComment()" class="rectangle-small-btn commentCancle-btn">
	        취소
	    </div>
    </div>

</form>