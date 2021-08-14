package ez.board.service02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ez.loader.JdbcUtil;
import ez.jdbc.connection.ConnectionProvider;

public class IdGenerator {
	private static IdGenerator instance = new IdGenerator();
	public static IdGenerator getInstance() {
		return instance;
	}
	
	private IdGenerator() {}
	//IdGenerator Ŭ������ ����� ������ �������� sequence_name Į���� �Է��� 'article'���� ����Ѵ�.
	
	//���� �׷��ȣ �����޼ҵ�.
	public int generateNextId(String sequenceName)throws IdGenerationFailedException{
		Connection conn = null;
		PreparedStatement pstmtSelect = null;
		ResultSet rs = null;
		PreparedStatement pstmtUpdate = null;
		
		try {
			conn = ConnectionProvider.getConnection();//DB����
			conn.setAutoCommit(false);//Ʈ����� ������
			pstmtSelect = conn.prepareStatement(//������ �غ�
					"select next_value from id_sequence " +
					" where sequence_name = ? for update ");
			pstmtSelect.setString(1, sequenceName);
			/*select next_value from id_sequence where sequence_name = 'article';
			  ��� ����, next_value = 26 */
			rs = pstmtSelect.executeQuery();
			rs.next();
			int id = rs.getInt(1);//���� id�� getInt(1)�� 26�� ����
			//System.out.println("rs����� �۹�ȣ" + id);26
			id++;//�۹�ȣ ����
			//System.out.println("������ �۹�ȣ" + id);27
			pstmtUpdate = conn.prepareStatement(
					" update id_sequence set next_value = ? "+
					" where sequence_name = ?");//�۹�ȣ ������ ���� ������
			pstmtUpdate.setInt(1, id);
			pstmtUpdate.setString(2, sequenceName);
			pstmtUpdate.executeUpdate();
			//System.out.println("�������� ��� �۹�ȣ" + id);27
			conn.commit();
			return id;
			}catch(SQLException ex) {
			JdbcUtil.rollback(conn);
			throw new IdGenerationFailedException(ex);
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(true);
				}catch(SQLException e) {
				}JdbcUtil.close(conn);
			}
		}
	}//public int generateNextId(String sequenceName)
	
	
}
