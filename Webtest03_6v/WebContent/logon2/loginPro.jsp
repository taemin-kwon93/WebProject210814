<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- �α����� ���� ������ -->

<!-- �α��� ���� -->
<c:if test="${check==1}">
	<c:set var="memId" value="${id}" scope="session"/>
	<meta http-equiv="refresh" content="0;url=/Webtest03_6v/logon2/main.do"/>
</c:if>

<!-- ��й�ȣ �ٸ� -->
<c:if test="${check == 0}">
	<script>
		alert("��й�ȣ�� ���� �ʽ��ϴ�.");
		history.go(-1);
	</script>
</c:if>

<!-- ��й�ȣ �ٸ� -->
<c:if test="${check == -1}">
	<script>
		alert("���̵� ���� �ʽ��ϴ�.");
		history.go(-1);
	</script>
</c:if>