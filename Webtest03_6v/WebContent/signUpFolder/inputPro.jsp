<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="ez.logon.LogonDBBean" %>
<%@ page import="java.sql.Timestamp" %>

<% request.setCharacterEncoding("euc-kr"); %>

<jsp:useBean id="member" class="ez.logon.LogonDataBean">
<jsp:setProperty name="member" property="*" />
</jsp:useBean>


<%
	member.setReg_date(new Timestamp(System.currentTimeMillis()));
	LogonDBBean manager = LogonDBBean.getInstance();
	manager.insertMember(member);
	
	response.sendRedirect("loginForm.jsp");
%>