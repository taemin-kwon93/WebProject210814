<%@ page contentType="text/html; charset=euc-kr" %>
<html>
<head><title>���� �޽��� ���� Ȯ��</title></head>
<body>

<form action="deleteMessage.jsp" method="post">
<input type="hidden" name="messageId" value=<%=request.getParameter("messageId") %>>
�޽����� �����Ϸ��� ��ȣ�� �Է��ϼ��� <br>
��ȣ : <input type="password" name="password" >
<input type="submit" value="�޽��� �����ϱ�" >




</form>


</body>


</html>