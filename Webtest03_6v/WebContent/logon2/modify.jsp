<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./color.jspf"%>

<html>
<head>
<title>��������</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_c}">
	<table width=500 cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td>
				<form name="myform" action="/Webtest03_6v/logon2/modifyForm.do"
					method="post">
					<input type="hidden" name="id" value="${sessionScope.memId}">
					<input type="submit" value="ȸ������ ����">
				</form>
			</td>
			<td>
				<form name="myform" action="/Webtest03_6v/logon2/deleteForm.do"
					method="post">
					<input type="hidden" name="id" value="${sessionScope.memId}">
					<input type="submit" value="ȸ�� Ż��">
				</form>
			</td>
			<td>
				<form name="myform" action="/Webtest03_6v/logon2/main.do" method="post">
					<input type="submit" value="��������">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>