package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {

	private MemberDao memberDao = new MemberDao();

	public User login(String id, String password) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(conn, id);
			if (member == null) {
				throw new LoginFailException();
			}
			if (!member.matchPw(password)) {
				throw new LoginFailException();
			}
			return new User(
					member.getMemno(),
					member.getNickname(),
					member.getId(),
					member.getPhonenum(),
					member.getEmail(),
					member.getPw(),
					member.getName(),
					member.getAddress(),
					member.getCompanyno(),
					member.getBirth(),
					member.getMemimg(),
					member.getRank());	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
