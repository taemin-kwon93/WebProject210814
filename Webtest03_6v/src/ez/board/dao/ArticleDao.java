package ez.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ez.model.Article;
import ez.loader.JdbcUtil;

public class ArticleDao {

	private static ArticleDao instance = new ArticleDao();
	public static ArticleDao getInstance() {
		return instance;
	}

	private ArticleDao() {
	}

	//�� ���� ����
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from article");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Article> select(Connection conn, int firstRow, int endRow)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title from ( "
					+ "    select rownum rnum, article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title from ( "
					+ "        select * from article m order by m.sequence_no desc "
					+ "    ) where rownum <= ? "
					+ ") where rnum >= ?");//46~50���� �������� 
			/*
			 * select * from article m order by m.sequence_no desc; ��������ȣ(�׷��ȣ+��ۺз���ȣ, 16�ڸ��� ����)�� ������������ �����Ѵ�.
			 * ��, ū ������ ���� ������ �� �����ϱ⶧���� �ֱ� �Էµ� ���� ���� ��Ÿ����.
			 * ���� �������� �ٽ�, select rownum rnum�� where rownum <= ? �̴�. endRow�� �������� 30���� article������ ����´�.
			 * ���������� endRow=30,firstRow=21 �̶�� (8��1�� 20:47������ ����) 3�������� �۵��� ���´�. */
			
			
			pstmt.setInt(1, endRow);//�������� ��� ���� 1
			pstmt.setInt(2, firstRow);//�������� ��� ���� 2
			rs = pstmt.executeQuery();//�������� ����� ��´�.
			if (!rs.next()) {//�������� ����� ���� �ÿ��� �ش��ϴ� �����Ͱ� ���� ���̴�.
				return Collections.emptyList();//����, ����ִ� List�� �����Ѵ�.
			}//int z = 1;
			List<Article> articleList = new ArrayList<Article>();//Article��ü Ÿ���� List���� articleList�� �����Ѵ�.
			do {
				Article article = makeArticleFromResultSet(rs, false);/*������ ����� ���� �����͵��� makeArticleFromResultSet �޼ҵ带 
				Ȱ���Ͽ� ArticleŸ�� ���� article�� ��´�. */
				articleList.add(article);//�ݺ��ؼ� article���� articleList�� ��´�.
				//System.out.println("�۴�� �׽�Ʈ: " + z++);
				//System.out.println(article);
			} while (rs.next());
			//System.out.println("���: " + articleList);
			//System.out.println("�۴�� �׽�Ʈ 2 : " + z);
			return articleList;
			/*  �۴�� �׽�Ʈ: 1
				�۴�� �׽�Ʈ: 2
				�۴�� �׽�Ʈ: 3
				�۴�� �׽�Ʈ: 4
				�۴�� �׽�Ʈ: 5
				�۴�� �׽�Ʈ: 6
				�۴�� �׽�Ʈ: 7
				�۴�� �׽�Ʈ: 8
				�۴�� �׽�Ʈ: 9
				�۴�� �׽�Ʈ: 10
				���: [ez.model.Article@700c0a90, ez.model.Article@803bf85, ez.model.Article@59f89575,
				 	 ez.model.Article@423e98e, ez.model.Article@146b2b10, ez.model.Article@181518f, 
				 	 ez.model.Article@1c4f2ff7, ez.model.Article@7bbda69, ez.model.Article@4a455225, ez.model.Article@41c37fcb]
				�۴�� �׽�Ʈ 2 : 11*/
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	//PDF���� 161������
	//������ ����κ��� �� �����(�����ϱ�)
	private Article makeArticleFromResultSet(ResultSet rs, boolean readContent)
			throws SQLException {
		Article article = new Article();
		article.setId(rs.getInt("article_id"));
		article.setGroupId(rs.getInt("group_id"));
		article.setSequenceNumber(rs.getString("sequence_no"));
		article.setPostingDate(rs.getTimestamp("posting_date"));
		article.setReadCount(rs.getInt("read_count"));
		article.setWriterName(rs.getString("writer_name"));
		article.setPassword(rs.getString("password"));
		article.setTitle(rs.getString("title"));
		if (readContent) {
			article.setContent(rs.getString("content"));
		}
		return article;
	}//������� list.jsp�� ���� �⺻ ������

	public int insert(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into article "//article ���̺� �����Ѵ�.
					+ "(article_id, group_id, sequence_no, posting_date, read_count, "
					+ "writer_name, password, title, content) "//�ش�Ǵ� �÷��鿡,
					+ "values (article_id_seq.NEXTVAL, ?, ?, ?, 0, ?, ?, ?, ?)");//��ȣ �� ���� �ִ´�.
			pstmt.setInt(1, article.getGroupId());
			pstmt.setString(2, article.getSequenceNumber());
			pstmt.setTimestamp(3, new Timestamp(article.getPostingDate().getTime()));
			pstmt.setString(4, article.getWriterName());
			pstmt.setString(5, article.getPassword());
			pstmt.setString(6, article.getTitle());
			pstmt.setString(7, article.getContent());
			int insertedCount = pstmt.executeUpdate();//�Է��� �Ǹ� 1�� ���ϵȴ�. executeUpdate()�� ���ϰ��� intŸ���̴�.
			//(1) DML(SQL Data Manipulation Language) ���� �� ���� �Ǵ� (2) �ƹ��͵� ��ȯ���� �ʴ� SQL ���� ��� 0
			//System.out.println("��ϵ� �� ��: " + insertedCount);
			//System.out.println("ArticleDao: " + article.getContent());
			if (insertedCount > 0) {//pstmt.executeUpdate()�� ����Ǿ� 1�� �Է��� �Ǹ�,
				stmt = conn.createStatement();//�Ʒ��� �������� �����Ѵ�.
				rs = stmt.executeQuery("select article_id_seq.CURRVAL from dual");//��� �Է��� ���� ������ ���� ������ �Ͷ�.
				if (rs.next()) {
					return rs.getInt(1);//���Ե� ������ ������ ���� ������, �� ���� �����ϰ�
				}
			}
			return -1;//�� ������ ����� ���� �ʾҴٸ�, -1�� �����Ѵ�.
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	//ReadArticleService���� ����ϴ� �޼ҵ�
	public Article selectById(Connection conn, int articleId)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from article where article_id = ?");
			pstmt.setInt(1, articleId);
			/*	����, select * from article where article_id = 42 �� ���
			  42	22	0000000022989898	21/07/30	1	����°	1	re: ����°	����° */
			rs = pstmt.executeQuery();//������ ����� ���� ������ rs�� ��´�.
			if (!rs.next()) {//articleId ������ ������ ������ ����� ������, �� ���� ������
				return null;//null���� �����Ѵ�.
			}
			Article article = makeArticleFromResultSet(rs, true);//���� ���� ���,
			//article.setId(rs.getInt("article_id")); �̷������� article�� ���� ��ƿ�.
			//private Article makeArticleFromResultSet(ResultSet rs, boolean readContent), return article;
			//System.out.println(article);, �Ź� �ٸ� �ּҰ��� ��µ�
			return article;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//��ȸ���� �ø������� �޼ҵ�
	public void increaseReadCount(Connection conn, int articleId)
			throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update article set read_count = read_count + 1 where article_id = ?");
			pstmt.setInt(1, articleId);//�� �������� �ٽ� -> �ش�Ǵ� �۹�ȣ�� read_count���� 1�� �����ش�.
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}//public void increaseReadCount

	public String selectLastSequenceNumber(Connection conn,
			String searchMaxSeqNum, String searchMinSeqNum) 
	throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select min(sequence_no) from article "
							+ "where sequence_no < ? and sequence_no >= ?");
			pstmt.setString(1, searchMaxSeqNum);
			pstmt.setString(2, searchMinSeqNum);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			return rs.getString(1);
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}//������ ����

	public int update(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("update article "
					+ "set title = ?, content = ? where article_id = ?");
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getId());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int articleId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete from article "
					+ "where article_id = ?");
			pstmt.setInt(1, articleId);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

}
