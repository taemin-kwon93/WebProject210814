package ez.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ez.action.CommandAction;

public class LogonInputFormAction implements CommandAction {//È¸¿ø°¡ÀÔ Æû Ã³¸®

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
     
        return "/logon2/inputForm.jsp";//ÇØ´ç ºä
    }
}
