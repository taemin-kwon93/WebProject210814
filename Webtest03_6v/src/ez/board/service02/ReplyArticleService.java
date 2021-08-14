package ez.board.service02;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

import ez.board.dao.ArticleDao;
import ez.model.Article;
import ez.model.ReplyingRequest;
import ez.loader.JdbcUtil;
import ez.jdbc.connection.ConnectionProvider;
//�亯�� �޾��ִ� ������ ����ִ�.
public class ReplyArticleService {
	private static ReplyArticleService instance = new ReplyArticleService();
	public static ReplyArticleService getInstance() {
		return instance;
	}
	
	private ReplyArticleService() {}
	//reply�޼ҵ忡 �ڹٺ� ��üŸ���� ����ش�.
	//ArticleNotFoundException ��ƼŬ�� ������,
	//CannotReplyArticleException �亯�� ������,
	//LastChildAleadyExistsException �亯�� �亯�� �亯 ���ñ� ���ñ�
	public Article reply(ReplyingRequest replyingRequest)throws ArticleNotFoundException, 
		CannotReplyArticleException,LastChildAleadyExistsException{//����Է��� ���ܰ�� 
		//1. ���̾��°��, 2. ����� �� �� ���� ���, 3. ������ ����� �̹� �ִ� ���.
		Connection conn = null;
		
		Article article = replyingRequest.toArticle();//ReplyingRequest�� WritingRequest�� ���� ��ӹ޴´�.
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//Ʈ����� ������
			
			ArticleDao articleDao = ArticleDao.getInstance();
			Article parent = articleDao.selectById(conn, replyingRequest.getParentArticleId());
			//int i = (parent.getId());
			//System.out.println("����� ���̵�(�۹�ȣ) ��" + i);
			/* ReplyingRequest�� WritingRequest�� ��ӹ޴´�.
			 * ReplyingRequest�� ���� �޾ƿ� int������ selectById�޼ҵ带 �����Ѵ�.
			 * ����� ����� ����� article�� parent�� �����Ѵ�.*/
			try {
				checkParent(parent, replyingRequest.getParentArticleId());
			}catch(Exception e) {//����ó��
				JdbcUtil.rollback(conn);//���ܰ� �߻����� ��, rollbackó�� �Ѵ�.
				if(e instanceof ArticleNotFoundException) {//������ ��� �з� 1
					throw(ArticleNotFoundException)e;
				}else if(e instanceof CannotReplyArticleException) {//������ ��� �з� 2
					throw(CannotReplyArticleException)e;
				}else if(e instanceof LastChildAleadyExistsException) {//������ ��� �з� 3
					throw(LastChildAleadyExistsException)e;
				}
			}
			String searchMaxSeqNum = parent.getSequenceNumber();//0000000026999999 �Ʒ������� ������ �ѹ� �������
			String searchMinSeqNum = getSearchMinSeqNum(parent);//���� ����(parent.getLevel())�� ���� ��������ȣ�� �����ؿ´�.
			
			String lastChildSeq = articleDao.selectLastSequenceNumber(conn, searchMaxSeqNum, searchMinSeqNum);
			String sequenceNumber = getSequenceNumber(parent, lastChildSeq);
			
			article.setGroupId(parent.getGroupId());
			article.setSequenceNumber(sequenceNumber);
			article.setPostingDate(new Date());
			
			int articleId = articleDao.insert(conn, article);
			if(articleId == -1) {
				throw new RuntimeException("DB ���� �ȵ�: " + articleId);
			}
			conn.commit();
			article.setId(articleId);
			return article;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException("DB �۾� ����: " + e.getMessage(), e);
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
				}catch(SQLException e) {}
			}
			JdbcUtil.close(conn);
		}
	}//public Article reply(ReplyingRequest replyingRequest)
	private void checkParent(Article parent, int parentId)throws ArticleNotFoundException, 
		CannotReplyArticleException, LastChildAleadyExistsException{
		
		if(parent == null) {
			throw new ArticleNotFoundException(
					"�θ���� �������� ����: " + parentId);
		}
		int parentLevel = parent.getLevel();
		if(parentLevel == 3) {
			throw new CannotReplyArticleException(
					"������ ���� �ۿ��� ����� �� �� �����ϴ�. " + parent.getId());
		}
	}//private void checkParent(Article parent, int parentId)
	
	private String getSearchMinSeqNum(Article parent){//�Ű������� �޾ƿ� �θ���� �����ϴ� �޼ҵ�
		String parentSeqNum = parent.getSequenceNumber();//�������ѹ��� String�� 16�����̴�. parent�� ������ ��ȣ�� ���� Ÿ���� ���� parentSeqNum�� �����Ѵ�.
		DecimalFormat decimalFormat = new DecimalFormat("0000000000000000");//0���� ä���� 16�ڸ��� ���� ����
		long parentSeqLongValue = Long.parseLong(parentSeqNum);//StringŸ���� Long���� ��ȯ�ϰ� �� ���� parentSeqLongValue�� �ִ´�.
		/*������ ��ȣ Ȯ��*///System.out.println("test1_parentSeqNum: "+ parentSeqLongValue);
		long searchMinLongValue = 0;
		switch (parent.getLevel()) {
		case 0://�������϶�, ���� ������ 0
			searchMinLongValue = parentSeqLongValue/1000000L * 1000000L;
			break;
		case 1://�������� ����϶� ������ 1
			searchMinLongValue = parentSeqLongValue/10000L*10000L;
			break;
		case 2:
			searchMinLongValue = parentSeqLongValue / 100L * 100L;
			break;
		}
		//System.out.println("test2_searchMinLongValue: " + searchMinLongValue);
		return decimalFormat.format(searchMinLongValue);
	}//getSearchMinSeqNum ó���Ҷ� system.out.println ���� ���� ����.
	
	/*getSearchMinSeqNum �޼ҵ� ó�� ��� �׽�Ʈ ����
	long parentSeqLongValue =50999999L;
	long searchMinLongValue;
	long x = parentSeqLongValue/1000000L;
	long y = x*1000000L;
	System.out.println("1: " + x);
	System.out.println("2: " + y);
	searchMinLongValue = parentSeqLongValue/1000000L * 1000000L;
	System.out.println("3: " + searchMinLongValue);
	
	DecimalFormat decimalFormat = new DecimalFormat("0000000000000000");
	System.out.println(decimalFormat.format(searchMinLongValue));
	[��°��]
	1: x=50
	2: y=50000000
	3: 50000000
	0000000050000000	*/
	
	private String getSequenceNumber(Article parent, String lastChildSeq)throws LastChildAleadyExistsException{
		long parentSeqLong = Long.parseLong(parent.getSequenceNumber());
		int parentLevel = parent.getLevel();
		
		long decUnit = 0;
		if(parentLevel == 0) {
			decUnit = 10000L;
		}else if(parentLevel == 1) {
			decUnit = 100L;
		}else if(parentLevel == 2) {
			decUnit = 1L;
		}
		
		String sequenceNumber = null;
		
		DecimalFormat decimalFormat = new DecimalFormat("0000000000000000");
		if(lastChildSeq == null) {//�亯���� ���ٸ�
			sequenceNumber = decimalFormat.format(parentSeqLong-decUnit);	
		}else {// �亯�� ����.
			//������ �亯�� ���� Ȯ��
			String orderOfLastChildSeq = null;
			if(parentLevel ==0) {
				orderOfLastChildSeq = lastChildSeq.substring(10, 12);
				sequenceNumber = lastChildSeq.substring(0, 12)+"9999";
			}else if(parentLevel == 1) {
				orderOfLastChildSeq = lastChildSeq.substring(12, 14);
				sequenceNumber = lastChildSeq.substring(0, 14) + "99";
			}else if(parentLevel == 2) {
				orderOfLastChildSeq = lastChildSeq.substring(14, 16);
				sequenceNumber = lastChildSeq;
			}
			
			if(orderOfLastChildSeq.equals("00")) {
				throw new LastChildAleadyExistsException(
						"������ �ڽ� ���� �̹� �����մϴ�." + lastChildSeq);
			}
			long seq = Long.parseLong(sequenceNumber) - decUnit;
			sequenceNumber = decimalFormat.format(seq);
		}
		return sequenceNumber;
	}//private String getSequenceNumber(Article parent, String lastChildSeq)

}