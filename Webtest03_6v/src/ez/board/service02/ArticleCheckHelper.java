package ez.board.service02;

import java.sql.Connection;
import java.sql.SQLException;

import ez.board.dao.ArticleDao;
import ez.model.Article;

public class ArticleCheckHelper {
	
	//�����Ҷ� �� �ʿ��� �޼ҵ��.
	public Article checkExistsAndPassword(Connection conn, int articleId, String password) throws
		SQLException, ArticleNotFoundException, InvalidPasswordException{
		
		ArticleDao articleDao = ArticleDao.getInstance();
		Article article = articleDao.selectById(conn, articleId);//�۹�ȣ(articleId)�� �ش��ϴ� article(��)�� ������´�.
		if(article == null) {
			throw new ArticleNotFoundException(
				"�Խñ��� �������� �ʽ��ϴ�. : " +articleId);
		}
		if(!checkPassword(article.getPassword(), password)) {
			//�Է��� ��й�ȣ(password)�� DB���� ������� ��й�ȣ(article.getPassword())�� ���Ѵ�.
			throw new InvalidPasswordException("�߸��� ��ȣ");
		}
		return article;
	}//public Article checkExistsAndPassword(Connection conn, int articleId, String password)
	
	private boolean checkPassword(String realPassword, String userInputPassword) {
		//realPassword�� DB���� ����� ��й�ȣ, userInputPassword�� ����ڰ� ������ ��й�ȣ
		if(realPassword == null) {
			return false;
		}
		if(realPassword.length() == 0) {
			return false;
		}
		return realPassword.equals(userInputPassword);
	}
}
