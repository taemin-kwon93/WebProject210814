package ez.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ez.action.CommandAction;

public class LogonModifyAction implements CommandAction {//회원정보 수정 페이지 처리

    public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable {

        return "/logon2/modify.jsp";//해당 뷰
    }
}
