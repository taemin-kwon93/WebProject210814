<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="ez.model.ArticleListModel" %>
<%@ page import="ez.board.service02.ListArticleService" %>
<%
	String pageNumberString = request.getParameter("p");//request�������� ���� p���� �޾ƿ´�. 
	//�޾ƿ� p���� StringŸ�� pageNumberString�� �����Ѵ�.
	int pageNumber=1;//�⺻ ������ ����, �Խ����� ���� 1�������� ������.
	if(pageNumberString != null && pageNumberString.length() > 0){//p���� �޾Ƽ� String������ ���� ���� �ִٸ�,
		pageNumber = Integer.parseInt(pageNumberString);//����ȭ �ؼ� ���� pageNumber�� �����Ѵ�.
	}
	
	ListArticleService listService = ListArticleService.getInstance();
	ArticleListModel articleListModel = listService.getArticleList(pageNumber);/*ListArticleService�� �ִ� getArticleList�޼ҵ忡
	(pageNumber)�Ű����� ���� �ְ� �� ��� ���� ArticleListModel(DTO)Ÿ�� ���� articleListModel�� �����Ѵ�. */
	request.setAttribute("listModel", articleListModel);//�ٽ�
	
	if(articleListModel.getTotalPageCount() > 0){
		int beginPageNumber = (articleListModel.getRequestPage() -1)/10*10+1;//���������� ����
		//System.out.println("articleListModel.getRequestPage(): " + articleListModel.getRequestPage());
		int endPageNumber = beginPageNumber + 9;//�� ������ ����
		//System.out.println("endPageNumber: " + endPageNumber);
		if(endPageNumber > articleListModel.getTotalPageCount()){//endPageNumber�� TotalPage���� ������ TotalPage���� ����ش�.
			endPageNumber = articleListModel.getTotalPageCount();
		}//System.out.println("endPageNumber: " + endPageNumber);
		request.setAttribute("beginPage", beginPageNumber);//������������ request ������ ����
		request.setAttribute("endPage", endPageNumber);//�� �������� request ������ ����
	}
%>
<jsp:forward page="list_view.jsp"/><!-- �ٽ� jsp:forward -->
<%--  �޸� ����
1. redirect�� forward�� �������� ũ�� �ΰ����� ���� �� �ִ�.
ù°, URL�� ��ȭ����(redirect(O), forward(X))
��°, ��ü�� ���뿩��(���� O -> forward, ���� X -> redirect) 

2. �ý���(session, DB)�� ��ȭ�� ����� ��û(�α���, ȸ������, �۾���)�� ��� 
redirect������� �����ϴ� ���� �ٶ����ϸ�, �ý��ۿ� ��ȭ�� ������ �ʴ� 
�ܼ���ȸ(����Ʈ����, �˻�)�� ��� forward������� �����ϴ� ���� �ٶ����ϴ�.--%>