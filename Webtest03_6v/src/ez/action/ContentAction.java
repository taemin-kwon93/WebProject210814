package ez.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ez.board.BoardDBBean;
import ez.board.BoardDataBean;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
//System.out.println("1 num 내용: "+num);
//System.out.println("2 pageNum 내용: "+pageNum);
		BoardDBBean dbPro = BoardDBBean.getInstance();
		BoardDataBean article = dbPro.getArticle(num);
//System.out.println("3 article 주소: "+article);		
		request.setAttribute("num", new Integer(num));//new연산자와 Integer를 왜 사용할까?
		request.setAttribute("pageNum", new Integer(pageNum));//new연산자를 왜 사용할까?
		//시험해볼 내용, 19행과 20행에서 new연산자와 Integer를 뺀 다음 결과물을 확인하자.
		request.setAttribute("article", article);
//System.out.println("4 setAttribute article 주소: "+article);
//System.out.println("article의 번호: "+article.getNum());
		return "/MVC/content.jsp";
	}
	
}
