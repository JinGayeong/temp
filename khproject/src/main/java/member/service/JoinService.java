package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {

	private MemberDao memberDao = new MemberDao();

	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member member = memberDao.selectById(conn, joinReq.getId());
			if (member != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}

			memberDao.insert(conn, new Member(
					joinReq.getMemno(),
					joinReq.getNickname(),
					joinReq.getId(), 
					joinReq.getPhonenum(),
					joinReq.getEmail(),
					joinReq.getPw(), 
					joinReq.getName(),
					joinReq.getAddress(),
					joinReq.getCompanyno(), 
					joinReq.getBirth(),
					joinReq.getMemimg(),
					joinReq.getRank()
					));
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}