package ez.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ez.logon.LogonDBBean;
import ez.action.CommandAction;

public class ConfirmIdAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("id");
		
		LogonDBBean dbPro = LogonDBBean.getInstance();
		int check = dbPro.confirmId(id);
		
		request.setAttribute("check", check);
		request.setAttribute("id", id);
		
		return "confirmId.jsp";
	}
}
