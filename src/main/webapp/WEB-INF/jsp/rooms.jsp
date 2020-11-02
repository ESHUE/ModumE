<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>채팅방 목록</h1>
<ul>
<c:forEach items="${rooms}" var="item">
<li><span onclick="chatListDetail('${item.id}','${member}')">${item.name}</span></li>
</c:forEach>
</ul>
<script>
/* 
 onclick으로 미리 id와 유저 정보를 넘긴다
 그 후에 js안의 변수 안에서 처리
*/
</script>