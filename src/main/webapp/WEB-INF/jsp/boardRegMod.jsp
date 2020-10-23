<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="board-regMod__form" action="" class="board-regMod__form">
    <div class="board-regMod-title">
        <div class="board-regMod-title__input">
            <input type="text" name="title" placeholder="제목을 입력하세요." onkeyup="changeTitle__cnt()">
        </div>
        <div class="board-regMod-title__cnt" id="board-regMod-title__cnt">
            0/100
        </div>
    </div>
    <div class="board-regMod-ctnt">
        <div class="board-regMod-ctnt__inputFile">
            <input type="text" id="ctnt-img-title" placeholder="이미지를 첨부하세요." readonly>
            <label class="cursor">
             <span class="material-icons">arrow_circle_up</span>
                <input type="file" onchange="javascript:document.getElementById('ctnt-img-title').value=this.value">
            </label>
        </div>
        <div class="board-regMod-ctnt__text">
            <textarea name="" id="board-regMod-ctnt__textarea" placeholder="내용을 입력하세요." onkeyup="changeCtnt__cnt()"></textarea>
        </div>
        <div class="board-regMod-ctnt__cnt" id="board-regMod-ctnt__cnt">
            0/10000
        </div>
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