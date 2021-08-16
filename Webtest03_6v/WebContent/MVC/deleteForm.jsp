<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="color.jspf" %>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="javascript">
function deleteSave(){
	if(document.delForm.passwd.value==''){
		alert("비밀번호를 입력하세요.");
		document.delForm.passwd.focus();
		return false;
	}
}
</script>
</head>

<body bgcolor="${bodyback_c}">
<center><b>글삭제</b><br>
<form method="post" name="delForm" action="/Webtest03_6v/MVC/deletePro.do?pageNum=${pageNum}" onsubmit="return deleteSave()">
<table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
<tr height="30">
	<td align="center" bgcolor="${value_c}">
		<b>비밀번호를 입력해 주세요.</b>
	</td>
</tr>
<tr height="30">
	<td align="center">비밀번호: 
		<input type="password" name="passwd" size="8" maxlength="12">
		<input type="hidden" name="num" value="${num}">
	</td>
</tr>
<tr height="30">
	<td align="center" bgcolor="${value_c}">
		<input type="submit" value="글삭제">
		<input type="button" value="글목록" onclick="document.location.href='/Webtest03_6v/MVC/list.do?pageNum=${pageNum}'">
	</td>
</table>
</form>
</body>
</html>