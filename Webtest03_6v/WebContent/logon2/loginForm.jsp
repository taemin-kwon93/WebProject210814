<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./color.jspf" %>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인</title>
<link href="style.css" rel="stylesheet" type="text/css">
   <script language="javascript">
   	
   function begin(){
   		document.myform.id.focus();
   	}
   	
   	function checkIt(){
   		if(!document.myform.id.value){
   			alert("이름을 입력하지 않으셨습니다.");
   			document.myform.id.focus();
   			return false;
   		}
   	}
   
   	</script>
</head>
<body onload="begin()" bgcolor="${bodyback_c}">
<form name="myform" action="/Webtest03_6v/logon2/loginPro.do" method="post" onSubmit="return checkIt()">
<table cellspacing="0" cellpadding="1" width="260" border="1" align="center">
	<tr height="30">
		<td colspan="2" align="middle" bgcolor="${title_c}"><strong>회원로그인</strong></td></tr>
  	<tr height="30">
	    <td width="110" bgcolor="${title_c}" align=center>아이디</td>
	    <td width="150" bgcolor="${value_c}" align=center>
	    	<input type="text" name="id" size="15" maxlength="12"></td></tr>
  <tr height="30">
	   	<td width="110" bgcolor="${title_c}" align=center>비밀번호</td>
	    <td width="150" bgcolor="${value_c}" align=center>
	    	<input type=password name="passwd"  size="15" maxlength="12"></td></tr>
  <tr height="30">
    <td colspan="2" align="middle" bgcolor="${title_c}" >
      <input type=submit value="로그인"> 
      <input type=reset value="다시입력">
      <input type="button" value="회원가입" onclick="document.location.href='/Webtest03_6v/logon2/inputForm.do'"></td></tr>
</table>
</form>
</body>
</html>