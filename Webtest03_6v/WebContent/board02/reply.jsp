<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="ez.board.service02.ReplyArticleService" %>
<%@ page import="ez.model.Article" %>
<% request.setCharacterEncoding("euc-kr"); %>
<%--read_view.jsp�� <a>�±׷� ���� ���۵ȴ�.
<a href="reply_form.jsp?parentId=${article.id}&p=${param.p}">�亯����</a>
reply_form.jsp�� ���� �޾ƿ� ����� Bean�� ����Ѵ�--%>
<jsp:useBean id="replyingRequest" class="ez.model.ReplyingRequest"/>
<jsp:setProperty name="replyingRequest" property="*"/>
<%
	String viewPage = null;//viewPage���� �� �������� �⺻������ null�� ����.
	try{
		Article postedArticle = ReplyArticleService.getInstance().reply(replyingRequest);
		request.setAttribute("postedArticle", postedArticle);//request
		viewPage = "reply_success.jsp";
	}catch(Exception ex){
		viewPage="reply_error.jsp";
		request.setAttribute("replyException", ex);
	}
%>
<jsp:forward page="<%= viewPage %>"/>