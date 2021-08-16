<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="color.jspf" %>

<html>
<head>
<meta charset="EUC-KR">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<style type='text/css'>
<!--
a:link { color:black; text-decoration:none; }
a:visited {  }
a:active { text-decoration:underline; }
a:hover { text-decoration:underline; background-image:url('text_dottdeline.gif'); background-repeat:repeat-x; background-position:50% 100%; }
-->
</style>
<style>
<!--
@font-face {font-family:굴림; src:url();}
body,td,a,div,p,pre,input,textarea {font-family:굴림;font-size:9pt;}
-->
</style>
</head>

<body bgcolor="${bodyback_c}"> 
 <center><b>글 내용 보기</b>
 <br>
 <form>
 <table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
 	<tr height="30"><!-- 글번호와 조회수 -->
		<td align="center" width="125" bgcolor="${value_c}">글번호</td> 	
		<td align="center" width="125" align="center">${article.num}</td>  	
		<td align="center" width="125" bgcolor="${value_c}">조회수</td>  	
		<td align="center" width="125" align="center">${article.readcount}</td>
	</tr>
 	<tr height="30"><!-- 작성자 작성일 -->
		<td align="center" width="125" bgcolor="${value_c}">작성자</td> 	
		<td align="center" width="125" align="center">${article.writer}</td>  	
		<td align="center" width="125" bgcolor="${value_c}">작성일</td>  	
		<td align="center" width="125" align="center">${article.reg_date}</td>
	</tr>
 	<tr height="30"><!-- 글제목 -->
		<td align="center" width="125" bgcolor="${value_c}">글제목</td> 	
		<td align="center" width="375" colspan="3">${article.subject}</td>  	
	</tr>
 	<tr height="30"><!-- 글내용 -->
		<td align="center" width="125" bgcolor="${value_c}">글내용</td> 	
		<td align="left" width="375" colspan="3"><pre>${article.content}</pre></td> 
	</tr>
	<tr height="30">
		<!-- 글수정 버튼 -->
		<td colspan="4" bgcolor="${value_c}" align="right">
		<input type="button" value="글수정" onclick="document.location.href='/Webtest03_6v/MVC/updateForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
		<!-- 글삭제 버튼 -->
		<input type="button" value="글삭제" onclick="document.location.href='/Webtest03_6v/MVC/deleteForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
		<!-- 답글 버튼 -->
		<input type="button" value="답글쓰기" 
		onclick="document.location.href='/Webtest03_6v/MVC/writeForm.do?num=${article.num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}'">&nbsp;&nbsp;&nbsp;&nbsp;
		<!-- 목록 버튼 -->
		<input type="button" value="글목록" onclick="document.location.href='/Webtest03_6v/MVC/list.do?pageNum=${pageNum}'">
		</td>
	</tr>
 	</table>
 </form>
</body>
</html>