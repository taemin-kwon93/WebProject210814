<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="ez.board.service02.WriteArticleService" %>
<%@ page import="ez.model.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="writingRequest" class="ez.model.WritingRequest">
<jsp:setProperty name="writingRequest" property="*"/>
</jsp:useBean><%-- writeForm���� �޾ƿ� ������� WritingRequest.java���� Ȱ���Ͽ� ��´�. --%>
<%
	Article postedArticle = 
			WriteArticleService.getInstance().write(writingRequest);
	request.setAttribute("postedArticle", postedArticle);
%>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խñ� �ۼ�</title>
</head>
<body>
�Խñ��� ����߽��ϴ�.
<br/>
<a href="<c:url value='list.jsp'/>">��Ϻ���</a>
<a href="<c:url value='read.jsp?articleId=${postedArticle.id}'/>">�Խñ��б�</a>
</body>
</html>