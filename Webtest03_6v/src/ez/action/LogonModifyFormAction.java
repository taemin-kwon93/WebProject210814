package ez.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ez.logon.LogonDataBean;
import ez.logon.LogonDBBean;
import ez.action.CommandAction;

public class LogonModifyFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("id");
		
		LogonDBBean dbPro = LogonDBBean.getInstance();
		LogonDataBean member = dbPro.getMember(id);
		
		request.setAttribute("member", member);
		return "/logon2/modifyForm.jsp";
	}
	
}
