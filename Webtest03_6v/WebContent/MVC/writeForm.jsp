<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file ="color.jspf" %>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>

<body bgcolor="${bodyback_c}">
<center><b>글쓰기</b>
<br>
<form method="post" name="writeForm" action="/Webtest03_6v/MVC/writePro.do" onsubmit="return writeSave()">
<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}"> 
<input type="hidden" name="re_step" value="${re_step}">  
<input type="hidden" name="re_level" value="${re_level}">  
<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
<tr>
	<td align="right" colspan="2" bgcolor="${value_c}">
	<a href="/Webtest03_6v/MVC/list.do">글목록</a></td>
</tr>
<tr>
	<td width="70" bgcolor="${value_c}" align="center">이름</td>
	<td width="330">
		<input type="text" size="10" maxlength="10" name="writer"></td>
</tr>
<!-- num의 조건에 따라 답변인지 일반 글인지 분별 -->
<tr>
	<td width="70" bgcolor="${value_c}" align="center">제목</td>
	<td width="330">
<c:if test="${num == 0}">
       <input type="text" size="40" maxlength="50" name="subject"></td>
</c:if>
<c:if test="${num != 0}">
   <input type="text" size="40" maxlength="50" name="subject" value="[답변]"></td>
</c:if>
</tr>

<tr>
	<td width="70" bgcolor="${value_c}" align="center">Email</td>
	<td width="330">
		<input type="text" size="40" maxlength="30" name="email"></td>
</tr>
<tr>
	<td width="70" bgcolor="${value_c}" align="center">내용</td>
	<td width="330">
		<textarea name="content" rows="13" cols="40"></textarea></td>
</tr>
<tr>
	<td width="70" bgcolor="${value_c}" align="center">비밀번호</td>
	<td width="330">
		<input type="password" size="8" maxlength="12" name="passwd"></td>
</tr>
<tr>
	<td colspan="2" bgcolor="${value_c}" align="center">
	<input type="submit" value="글쓰기">
	<input type="reset" value="다시작성">
	<input type="button" value="목록보기" OnClick="window.location='/Webtest03_6v/MVC/list.do'"></td>
</tr>
</table>
</form>
</body>
</html>