<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page errorPage = "../error/error_view.jsp" %>

<%@ page import = "ez.imageBoard.Theme" %>
<%@ page import = "ez.imageBoard.ThemeManager" %>
<%@ page import = "ez.imageBoard.ThemeManagerException" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>�ۻ���</title></head>
<body>
<%
    String themeId = request.getParameter("id");
    ThemeManager manager = ThemeManager.getInstance();
    Theme oldTheme = manager.select(Integer.parseInt(themeId));
%>
<c:set var="oldTheme" value="<%= oldTheme %>" />
<c:choose>
    <c:when test="${empty oldTheme}">
    <script>
    alert("���� �������� �ʽ��ϴ�.");
    location.href="list.jsp";
    </script>
    </c:when>

    <c:when test="${oldTheme.password != param.password}">
    <script>
    alert("��ȣ�� �ٸ��ϴ�.");
    history.go(-1);
    </script>
    </c:when>

    <c:when test="${oldTheme.password == param.password}">
    <%
        manager.delete(oldTheme.getId());
    %>
    <script>
    alert("���� �����Ͽ����ϴ�.");
    location.href="list.jsp";
    </script>
    </c:when>
</c:choose>
</body>
</html>