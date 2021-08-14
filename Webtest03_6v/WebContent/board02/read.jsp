<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="ez.board.service02.ReadArticleService" %>
<%@ page import="ez.board.service02.ArticleNotFoundException" %>
<%@ page import="ez.model.Article" %>
<%
	int articleId = Integer.parseInt(request.getParameter("articleId"));//list_view�� ���� �޾ƿ� �Ķ���� articleId�� intŸ�� articleId�� �����Ѵ�.
	//System.out.println("articleId: " + articleId); �о���� ���� articleId ���
	String viewPage = null;//forward�� �� page���� ���� ������ StringŸ�� ����
	try{
		Article article = ReadArticleService.getInstance().readArticle(articleId);//�Ű����� articleId�� ����ִ� article�� ����´�.
		request.setAttribute("article", article);//�ش� article�� request������ 'article'�� ����Ѵ�.
		viewPage = "read_view.jsp";//�� ������ �����ϰ� viewPage�� 'read_view.jsp'�� �����Ѵ�.
	}catch(ArticleNotFoundException ex){//ArticleNotFound ���ܰ� �߻��������� ó��
		viewPage = "article_not_found.jsp";//���ܰ� �߻����� ��, viewPage�� ���� 'article_not_found.jsp'�̴�.
	}

	//System.out.println("viewPage: " + viewPage);
%>
<jsp:forward page="<%=viewPage%>"/>