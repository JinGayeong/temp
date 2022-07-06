package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class DeleteMemberService {

	private MemberDao memberDao = new MemberDao();

	public void delete(String id, User authUser) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member member = memberDao.selectById(conn, id);
			if (member == null) {
				/* JdbcUtil.rollback(conn); */
				throw new DuplicateIdException();
			}

			memberDao.delete(conn, id);
			
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}



}
