package ez.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ez.action.CommandAction;

public class LogonInputFormAction implements CommandAction {//ȸ������ �� ó��

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
     
        return "/logon2/inputForm.jsp";//�ش� ��
    }
}
