<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:set var="exceptionType" value="${replyException.class.simpleName}" /> --%>
<%
	Exception deleteException = (Exception) request.getAttribute("deleteException");
	String exceptionType = deleteException.getClass().getSimpleName();
	request.setAttribute("exceptionType", exceptionType);
%>

<html>
<head>
<title>���� ����</title>
</head>
<body>
	����:
	<c:choose>
		<c:when test="${exceptionType == 'ArticleNotFoundException'}">
		������ �Խñ��� �������� �ʽ��ϴ�.
		</c:when>
		<c:when test="${exceptionType == 'InvalidPasswordException'}">
		��ȣ�� �߸� �Է��ϼ̽��ϴ�.
		</c:when>
	</c:choose>
	<br />
	<a href="<c:url value='list.jsp?p=${param.p}'/>">��Ϻ���</a>
</body>
</html>
