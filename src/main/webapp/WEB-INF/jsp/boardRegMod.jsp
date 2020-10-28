<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="//cdn.ckeditor.com/4.15.0/standard/ckeditor.js"></script>
<form id="board-regMod__form" action="" class="board-regMod__form" method="post">
    <div class="board-regMod-title">
        <div class="board-regMod-title__input">
            <input type="text" name="title" placeholder="제목을 입력하세요." onkeyup="changeTitle__cnt()">
        </div>
        <div class="board-regMod-title__cnt" id="board-regMod-title__cnt">
            0/100
        </div>
    </div>
    <div class="board-regMod-ctnt">
        <textarea name="board-regMod-ctnt" id="board-regMod-ctnt__textarea" placeholder="내용을 입력하세요."></textarea>
    </div>
    <div class="board-regMod__btns">
        <button type="button" class="btn-div flex-center cursor" onclick="changeLocation('/boardList')">
            취소
        </button>
        <button class="btn-div flex-center cursor">
            등록
        </button>
    </div>
</form>
