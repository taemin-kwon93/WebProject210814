<%@ page contentType = "text/html; charset=euc-kr" %>
<%
    request.setAttribute("name", "�ֹ���");
%>
<html>
<head><title>EL Object</title></head>
<body>

��û URI: ${pageContext.request.requestURI} <br>
<%--
	<%= pageContext.getRequest().getRequestURI()%>
 --%>
request�� name �Ӽ�: ${requestScope.name} <br>
<%--
	<%= request.getAttribute("name")%>
 --%>
code �Ķ����: ${param.code}
<%--
	<%= request.getParameter("code")%>
 --%>

</body>
</html>