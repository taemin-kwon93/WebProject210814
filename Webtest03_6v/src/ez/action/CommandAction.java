package ez.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
	//깃허브 연동 테스트3
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable;
	
}
