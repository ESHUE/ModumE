<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="comment-menu">
    <div class="comment-menu1">
        <div id="allComment" class="cursor bold" onclick="toggleFontWeight(this)">전체</div>
        <div id="myComment" class="cursor" onclick="toggleFontWeight(this)">내 댓글</div>
    </div>
    <div class="comment-menu2">
        <div id="showASC" class="cursor bold" onclick="toggleFontWeight(this)">오름차순</div>
        <div id="showDESC" class="cursor" onclick="toggleFontWeight(this)">내림차순</div>
    </div>
</div>

<div class="comment boardContents">
    <!-- 테이블 시작!!!! -->
    <table class="comment-table board-table">
        <tr class="board-detail-profile">
            <td class="board-profile-td">
                <div class="board-profile__box">
                    <img class="board-profile__img" src="img/test-profile.jpg" alt="프로필">
                </div>
            </td>
            <td class="board-user-name">은찌리</td>
            <td class="comment-modDel-td">
                <!-- 포문 돌릴때 아이디 값 조심 -->
                <button id="comment-modDel-td0" class="material-icons cursor"
                    onclick="showModDelBox(this)">more_horiz</button>
            </td>
        </tr>
        <tr>
            <td></td>
            <td colspan="2">동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 동해물과 백두산이 마르고 닳도록
                하느님이 보우하사 우리나라 만세 동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세
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
</div>
<form id="commentIns-form" class="comment-option-box flex-center">
    <div class="board-btns comment-backTop-btns">
        <!-- 뒤로가기 버튼 -->
        <div class="go-to-back board-detail-back cursor circle-btn50" onclick="changeLocation('/boardList')">
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
    <div onclick="formSubmit()" class="rectangle-small-btn commentIns-btn">
        등록
    </div>
</form>