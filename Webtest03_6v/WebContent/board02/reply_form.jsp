<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<html>
<head>
<meta charset="EUC-KR">
<title>�亯 ����</title>
</head>
<body>
<form action="reply.jsp" method="post">
<input type="hidden" name="parentArticleId" value="${param.parentId}"/>
<input type="hidden" name="p" value="${param.p}"/>
����:<input type="text" name="title" size="20" value="re: "/><br/>
�ۼ���:<input type="text" name="writerName" /><br/>
�۾�ȣ:<input type="password" name="password"/><br/>
�۳���:<br/>
<textarea name="content" cols="40" rows="5"></textarea><br/>
<input type="submit" value="����"/>
</form>
</body>
</html>