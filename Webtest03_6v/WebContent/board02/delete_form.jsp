<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="EUC-KR">
<title>�����ϱ�</title>
</head>
<body>
<form action="delete.jsp" method="post">
<input type="hidden" name="articleId" value="${param.articleId}"/>
�۾�ȣ: <input type="password" name="password"/><br/>
<input type="submit" value="����"/>
</form>
</body>
</html>