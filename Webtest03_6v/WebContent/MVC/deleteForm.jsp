<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="color.jspf" %>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ���</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="javascript">
function deleteSave(){
	if(document.delForm.passwd.value==''){
		alert("��й�ȣ�� �Է��ϼ���.");
		document.delForm.passwd.focus();
		return false;
	}
}
</script>
</head>

<body bgcolor="${bodyback_c}">
<center><b>�ۻ���</b><br>
<form method="post" name="delForm" action="/Webtest03_6v/MVC/deletePro.do?pageNum=${pageNum}" onsubmit="return deleteSave()">
<table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
<tr height="30">
	<td align="center" bgcolor="${value_c}">
		<b>��й�ȣ�� �Է��� �ּ���.</b>
	</td>
</tr>
<tr height="30">
	<td align="center">��й�ȣ: 
		<input type="password" name="passwd" size="8" maxlength="12">
		<input type="hidden" name="num" value="${num}">
	</td>
</tr>
<tr height="30">
	<td align="center" bgcolor="${value_c}">
		<input type="submit" value="�ۻ���">
		<input type="button" value="�۸��" onclick="document.location.href='/Webtest03_6v/MVC/list.do?pageNum=${pageNum}'">
	</td>
</table>
</form>
</body>
</html>