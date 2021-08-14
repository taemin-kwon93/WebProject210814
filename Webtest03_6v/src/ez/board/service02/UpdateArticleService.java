package ez.board.service02;

import java.sql.Connection;
import java.sql.SQLException;

import ez.board.dao.ArticleDao;
import ez.model.Article;
import ez.model.UpdateRequest;
import ez.loader.JdbcUtil;
import ez.jdbc.connection.ConnectionProvider;

public class UpdateArticleService {
	
	private static UpdateArticleService instance = new UpdateArticleService();
	public static UpdateArticleService getInstance() {
		return instance;
	}
	private UpdateArticleService() {}
	
	public Article update(UpdateRequest updateRequest)throws ArticleNotFoundException, InvalidPasswordException{
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();//DB����
			conn.setAutoCommit(false);//Ʈ����� ������
			
			ArticleCheckHelper checkHelper = new ArticleCheckHelper();
			checkHelper.checkExistsAndPassword(conn, updateRequest.getArticleId(), updateRequest.getPassword());
			
			Article updatedArticle = new Article();//�ڹٺ�ü updatedArticle�� �����
			updatedArticle.setId(updateRequest.getArticleId());//������ ������ ����ͼ� ����ְ� 
			updatedArticle.setTitle(updateRequest.getTitle());//����ְ�2
			updatedArticle.setContent(updateRequest.getContent());//����ְ�3
			
			ArticleDao articleDao = ArticleDao.getInstance();
			int updateCount = articleDao.update(conn, updatedArticle);
			if(updateCount == 0) {
				throw new ArticleNotFoundException(
					"�Խñ��� �������� �ʽ��ϴ� : " + updateRequest.getArticleId());
			}
			Article article = articleDao.selectById(conn, updateRequest.getArticleId());
			
			conn.commit();
			
			return article;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB���� �߻�: " + e.getMessage(), e);
		}catch(ArticleNotFoundException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}catch(InvalidPasswordException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
				}catch(SQLException e) {}
				JdbcUtil.rollback(conn);
			}
		}
	}
}
