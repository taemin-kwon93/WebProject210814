<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 로그인의 조건 세가지 -->

<!-- 로그인 성공 -->
<c:if test="${check==1}">
	<c:set var="memId" value="${id}" scope="session"/>
	<meta http-equiv="refresh" content="0;url=/Webtest03_6v/logon2/main.do"/>
</c:if>

<!-- 비밀번호 다름 -->
<c:if test="${check == 0}">
	<script>
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
</c:if>

<!-- 비밀번호 다름 -->
<c:if test="${check == -1}">
	<script>
		alert("아이디가 맞지 않습니다.");
		history.go(-1);
	</script>
</c:if>