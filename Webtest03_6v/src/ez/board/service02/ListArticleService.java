package ez.board.service02;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ez.board.dao.ArticleDao;
import ez.model.Article;
import ez.model.ArticleListModel;
import ez.loader.JdbcUtil;
import ez.jdbc.connection.ConnectionProvider;

public class ListArticleService {
	private static ListArticleService instance = new ListArticleService();
	public static ListArticleService getInstance() {
		return instance;
	}
	
	public static final int COUNT_PER_PAGE=10;
	
	//�� ����� �ҷ����� �޼ҵ�/*model��Ű��, ArticleListModel Ÿ������ ���� �޴´�.*/
	public ArticleListModel getArticleList(int requestPageNumber) {//intŸ�� ������ ���ڸ� �Ű������� �޴´�.
		if(requestPageNumber < 0) {//������ ���� 0���� ������ ����ó���� �Ѵ�.
			throw new IllegalArgumentException("page number < 0: " + requestPageNumber);//������ �ѹ��� 0���� �������� ����ó��
		}
		ArticleDao articleDao = ArticleDao.getInstance();//articleDao 
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int totalArticleCount = articleDao.selectCount(conn);//���� ����(select count(*)from article)
			//�� ����ͼ� totalArticleCount�� �����Ѵ�.
			
			if(totalArticleCount == 0) {//��ϵ� ���� �������� ���
				return new ArticleListModel();
			}
			
			int totalPageCount = calculateTotalPageCount(totalArticleCount);
			
			int firstRow = (requestPageNumber -1)*COUNT_PER_PAGE +1;
			/*������ �ѹ��� 2�϶� ��� ����. (2-1)*10(���count_per_page)+1 =11, ���� 2�������� ù��° ���� 11  */
			int endRow = firstRow + COUNT_PER_PAGE -1;//�� �ּ��� ���� ����. 11+10-1 = 20, ���� ������ ���� 20
			
			if(endRow > totalArticleCount) {//���� �� ���� ���� endRow�� ���� Ŭ��, ������ ��°�� �� ��(��, 34��°)�� �����Ѵ�.
				endRow = totalArticleCount;
			}
			
			List<Article> articleList = articleDao.select(conn, firstRow, endRow);
			//<Article>Ÿ���� List 'articleList'����, ���뿡�� articleDao.select�޼ҵ��� ����� ����.
			ArticleListModel articleListView = new ArticleListModel(articleList, requestPageNumber, totalPageCount, firstRow, endRow);
			return articleListView;
		}catch(SQLException e){
			throw new RuntimeException("DB���� �߻�: "+e.getMessage(), e);
		}finally {
			JdbcUtil.close(conn);
		}
	}//getArticleList
	
	//������ ���� �� ������ ����ϴ� �޼ҵ�
	private int calculateTotalPageCount(int totalArticleCount) {
		if(totalArticleCount==0) {//�Խñ��� ������, 0�� �����Ѵ�.
			return 0;
		}
		int pageCount = totalArticleCount/COUNT_PER_PAGE;
		/*����, ���� �� ������ 43�� �϶�,
		 * intŸ�� pageCount������ ���� 43/(���)10 �̴�.
		 * ���� pageCount�� ����� ���� 4�̴�.(%->������ 3)*/
		if(totalArticleCount%COUNT_PER_PAGE > 0) {
			pageCount++;//������ �� 3�� ���� �������߰� �����۾��� �Ѵ�.
		}
		return pageCount;/*���������� �Խñ� 10���� ����������(COUNT_PER_PAGE,
		�ҷ��� ��ü �� ��(totalArticleCount)�� 10���� 
		������. ����� ��� ����*/
	}
}
