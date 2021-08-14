package ez.board.service02;

import java.sql.Connection;
import java.sql.SQLException;

import ez.board.dao.ArticleDao;
import ez.model.Article;
import ez.loader.JdbcUtil;
import ez.jdbc.connection.ConnectionProvider;

public class ReadArticleService {
	private static ReadArticleService instance = new ReadArticleService();
	
	public static ReadArticleService getInstance() {
		return instance;
	}
	
	private ReadArticleService() {}
	
	public Article readArticle(int articleId) throws ArticleNotFoundException{
		return selectArticle(articleId, true);//���� ���� ���� boolean increaseCount�� true�� �༭ ��ȸ���� �ø���.
	}

	private Article selectArticle(int articleId, boolean increaseCount)throws ArticleNotFoundException{
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			ArticleDao articleDao = ArticleDao.getInstance();
			Article article = articleDao.selectById(conn, articleId);//�Ű������� �޾ƿ� articleId�� selectById�޼ҵ带 �����ϰ�
			//�޼ҵ��� ����� �޾ƿ� article�� �����Ѵ�.
			
			if(article == null) {//article�� ���̶��, �ش� ���̵�� ��ϵ� ���� ����.
				throw new ArticleNotFoundException(
						"�Խñ��� �������� �ʽ��ϴ�.: " + articleId);
			}
			if(increaseCount) {//��ȸ���� �ø���.
				articleDao.increaseReadCount(conn, articleId);
				article.setReadCount(article.getReadCount() +1 );
			}
			return article;//int articleId ���� ���� ���� �����Ѵ�.
		}catch(SQLException e) {
			throw new RuntimeException("DB ���� �߻�: " + e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}
	}//private Article selectArticle(int articleId, boolean increaseCount)
	
	public Article getArticle(int articleId) throws ArticleNotFoundException{
		return selectArticle(articleId, false);//getArticle�� ����Ҷ��� boolean increaseCount�� false�� �༭ ��ȸ���� �ø��� �ʴ´�.
	}
}
