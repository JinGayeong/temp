package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import member.model.MemberVO;

public class ChangePasswordService {

	private MemberDao memberDao = new MemberDao();

	public void changePassword(
			String userId, String newRank, String newCompanyno, String newId, String newNickname, String newPw,
			String newMemimg, String newName, String newBirth, String newAddress, String newPhonenum, String newEmail) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member member = memberDao.selectById(conn, userId);

			if (member == null) {
				throw new MemberNotFoundException();
			}

//			if (!member.matchPw(curPwd)) {
//				throw new InvalidPasswordException();
//			}

			member.changeCompanyno(newCompanyno);
			member.changeNickname(newNickname);
			member.changePw(newPw);
			member.changeMemimg(newMemimg);
			member.changeAddress(newAddress);
			member.changePhonenum(newPhonenum);
			member.changeEmail(newEmail);
			System.out.println("/*** member.toString()="+member.toString());
			memberDao.update(conn, member);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
