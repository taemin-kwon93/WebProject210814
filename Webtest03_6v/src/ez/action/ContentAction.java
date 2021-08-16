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
//System.out.println("1 num ����: "+num);
//System.out.println("2 pageNum ����: "+pageNum);
		BoardDBBean dbPro = BoardDBBean.getInstance();
		BoardDataBean article = dbPro.getArticle(num);
//System.out.println("3 article �ּ�: "+article);		
		request.setAttribute("num", new Integer(num));//new�����ڿ� Integer�� �� ����ұ�?
		request.setAttribute("pageNum", new Integer(pageNum));//new�����ڸ� �� ����ұ�?
		//�����غ� ����, 19��� 20�࿡�� new�����ڿ� Integer�� �� ���� ������� Ȯ������.
		request.setAttribute("article", article);
//System.out.println("4 setAttribute article �ּ�: "+article);
//System.out.println("article�� ��ȣ: "+article.getNum());
		return "/MVC/content.jsp";
	}
	
}
