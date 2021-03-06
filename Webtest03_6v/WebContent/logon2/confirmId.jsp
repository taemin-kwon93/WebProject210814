<%@ page contentType="text/html;charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./color.jspf"%> 
<html>
<head><title>ID 중복확인</title>
<link href="style.css" rel="stylesheet" type="text/css">

<body bgcolor="${bodyback_c}">
<!-- c:if 중복된 아이디일 경우 -->
<c:if test="${check==1}">
<table width="270" border="0" cellspacing="0" cellpadding="5">
	<tr bgcolor="${title_c}">
		<td height="39">${id}는 이미 사용중인 아이디입니다.</td>
	</tr>
</table>
<form name="checkForm" method="post" action="/Webtest03_6v/logon2/confirmId.do">
<table width="270" border="0" cellpadding="0" cellspacing="5">
<tr>
	<td bgcolor="${value_c}" align="center">
	<p>다른 아이디를 선택하세요.</p>
	<input type="text" size="10" maxlength="12" name="id">
	<input type="submit" value="ID중복확인">
	</td>
</tr>
</table>
</form>
</c:if>
<!-- c:if 사용 가능한 아이디일 경우 -->
<c:if test="${check == -1}"> 
<table width="270" border="0" cellspacing="0" cellpadding="5">
  <tr bgcolor="${title_c}"> 
    <td align="center"> 
      <p>입력하신 ${id} 는 사용하실 수 있는 ID입니다. </p>
      <input type="button" value="닫기" onclick="setid()">
    </td>
  </tr>
</table>
</c:if>
</body>
</html>
<script language="javascript">
  function setid()
    {		
    	opener.document.userinput.id.value="${id}";
	self.close();
   }
</script>