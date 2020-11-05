<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<h1>${room.name}(${room.id})</h1>
	<div class="view">
		<div class="content" id="content">
			<ul class="chat-box"></ul>
		</div>
	</div>
		<div class="chatInputDiv">
			<input name="message" id="inputMessage" placeholder="메세지를 입력하세요" >
			<button class="send" id="sendBtn">보내기</button>
		</div>
