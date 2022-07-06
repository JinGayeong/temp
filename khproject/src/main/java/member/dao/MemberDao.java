package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import member.model.Member;
import member.model.MemberVO;

public class MemberDao {

	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member(
						rs.getString("memno"),
						rs.getString("nickname"), 
						rs.getString("id"), 
						rs.getString("phonenum"),
						rs.getString("email"), 
						rs.getString("pw"), 
						rs.getString("name"), 
						rs.getString("address"),
						rs.getString("companyno"), 
						rs.getString("birth"), 
						rs.getString("memimg"), 
						rs.getString("rank"));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void insert(Connection conn, Member mem) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt =
					conn.prepareStatement("insert into member"
							+ "(memno, nickname, id, phonenum, email, pw, name, address, companyno, birth, memimg, rank) "
							+ "values((SELECT COUNT(*)+1 FROM MEMBER), ?,?,?,?,?,?,?,?,?,?,?)");
			
			
			MemberVO memberVO = new MemberVO();
			
			memberVO.setNickname(mem.getNickname());;
			memberVO.setId(mem.getId());
			memberVO.setPhonenum(Integer.parseInt(mem.getPhonenum()));
			memberVO.setEmail(mem.getEmail());
			memberVO.setPw(mem.getPw());
			memberVO.setName(mem.getName());
			memberVO.setAddress(mem.getAddress());
			memberVO.setCompanyno(mem.getCompanyno());
			memberVO.setBirth(Integer.parseInt(mem.getBirth()));
			memberVO.setMemimg(mem.getMemimg());
			memberVO.setRank(Integer.parseInt(mem.getRank()));
			pstmt.setString(1, 	memberVO.getNickname());                   
			pstmt.setString(2, 	memberVO.getId());                         
			pstmt.setInt(3, 	memberVO.getPhonenum()); 
			pstmt.setString(4, 	memberVO.getEmail());                      
			pstmt.setString(5, 	memberVO.getPw());                         
			pstmt.setString(6, 	memberVO.getName());                       
			pstmt.setString(7, 	memberVO.getAddress());                    
			pstmt.setString(8, 	memberVO.getCompanyno());                  
			pstmt.setInt(9,     memberVO.getBirth());        
			pstmt.setString(10, memberVO.getMemimg());      
			pstmt.setInt(11,    memberVO.getRank());        
			pstmt.executeUpdate();                                    
			
			System.out.println();
			conn.commit();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void update(Connection conn, Member mem) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update member set nickname = ?, phonenum = ?, email = ?, pw = ?, name = ?, address = ?, companyno = ?, birth = ?, memimg = ?, rank = ? where id = ?")) {
			pstmt.setString(1, mem.getNickname());
			pstmt.setInt(2, Integer.parseInt(mem.getPhonenum()));
			pstmt.setString(3, mem.getEmail());
			pstmt.setString(4, mem.getPw());
			pstmt.setString(5, mem.getName());
			pstmt.setString(6, mem.getAddress());
			pstmt.setString(7, mem.getCompanyno());
			pstmt.setInt(8, Integer.parseInt(mem.getBirth()));
			pstmt.setString(9, mem.getMemimg());
			pstmt.setInt(10, Integer.parseInt(mem.getRank()));
			pstmt.setString(11, mem.getId());
			pstmt.executeUpdate();
		}
	}
	
	
	public int delete(Connection conn, String id) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("delete from member where id = ?")) {
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}
	}

}
