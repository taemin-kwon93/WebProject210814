<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file ="color.jspf" %>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ���</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>

<body bgcolor="${bodyback_c}">
<center><b>�۾���</b>
<br>
<form method="post" name="writeForm" action="/Webtest03_6v/MVC/writePro.do" onsubmit="return writeSave()">
<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}"> 
<input type="hidden" name="re_step" value="${re_step}">  
<input type="hidden" name="re_level" value="${re_level}">  
<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
<tr>
	<td align="right" colspan="2" bgcolor="${value_c}">
	<a href="/Webtest03_6v/MVC/list.do">�۸��</a></td>
</tr>
<tr>
	<td width="70" bgcolor="${value_c}" align="center">�̸�</td>
	<td width="330">
		<input type="text" size="10" maxlength="10" name="writer"></td>
</tr>
<!-- num�� ���ǿ� ���� �亯���� �Ϲ� ������ �к� -->
<tr>
	<td width="70" bgcolor="${value_c}" align="center">����</td>
	<td width="330">
<c:if test="${num == 0}">
       <input type="text" size="40" maxlength="50" name="subject"></td>
</c:if>
<c:if test="${num != 0}">
   <input type="text" size="40" maxlength="50" name="subject" value="[�亯]"></td>
</c:if>
</tr>

<tr>
	<td width="70" bgcolor="${value_c}" align="center">Email</td>
	<td width="330">
		<input type="text" size="40" maxlength="30" name="email"></td>
</tr>
<tr>
	<td width="70" bgcolor="${value_c}" align="center">����</td>
	<td width="330">
		<textarea name="content" rows="13" cols="40"></textarea></td>
</tr>
<tr>
	<td width="70" bgcolor="${value_c}" align="center">��й�ȣ</td>
	<td width="330">
		<input type="password" size="8" maxlength="12" name="passwd"></td>
</tr>
<tr>
	<td colspan="2" bgcolor="${value_c}" align="center">
	<input type="submit" value="�۾���">
	<input type="reset" value="�ٽ��ۼ�">
	<input type="button" value="��Ϻ���" OnClick="window.location='/Webtest03_6v/MVC/list.do'"></td>
</tr>
</table>
</form>
</body>
</html>