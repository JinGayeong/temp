package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class ReadMypageService {
//여기선 db에서 값가져오고 그거 리퀘스트 변수에 넣는 거 해야됨.
	
	
	//멤버 다오 변수를 만듦.
	private MemberDao memberDao = new MemberDao();
	
	public MypageData getMypage(String memberId) {
		
		//DB에서 id값에 해당하는 값들을 가져와 member에 넣음.
		try (Connection conn = ConnectionProvider.getConnection()){
			Member member = memberDao.selectById(conn, memberId);
			if (member == null) {
				throw new MemberNotFoundException();
			}
			
			//그리고 그 멤버 데이터를 리턴함.
			return new MypageData(member);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
