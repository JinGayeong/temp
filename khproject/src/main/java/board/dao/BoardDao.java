package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import board.model.Board;
import board.model.Writer;
import jdbc.JdbcUtil;
import java.text.SimpleDateFormat;

public class BoardDao {

	public Board insert(Connection conn, Board board) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		// �����ͺ��̽� ���̺� ���� ��
		try { // article_no int auto_increment primary key, <-MySQL�� auto_increment�� �������ָ� �ڵ����� �ϳ��� + �ȴ�
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select boardNo_seq.nextval from dual");

			pstmt = conn
					.prepareStatement("insert into board (boardNo,id, nickname,memNo, title, regDate, modifiedDate," // 7��
							+ "category, address, area,startDate,endDate,budget,part,require,image,VIEW_COUNT)" // 10��
							+ "values (boardNo_seq.currval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1)");

//			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(1, board.getWriter().getId());
			pstmt.setString(2, board.getWriter().getNickname());
			pstmt.setInt(3, board.getWriter().getMemNo());
			pstmt.setString(4, board.getTitle());
			pstmt.setTimestamp(5, toTimestamp(board.getRegDate()));
			pstmt.setTimestamp(6, toTimestamp(board.getModifiedDate()));
			pstmt.setString(7, board.getCategory());
			pstmt.setString(8, board.getAddress());
			pstmt.setInt(9, board.getArea());
			pstmt.setString(10, board.getStartDate());
			pstmt.setString(11, board.getEndDate());
			pstmt.setString(12, board.getBudget());
			pstmt.setString(13, board.getPart());
			pstmt.setString(14, board.getRequire());
			pstmt.setString(15, board.getImage());
			
			int insertedCount = pstmt.executeUpdate();
			conn.commit();
			if (insertedCount > 0) {

				if (rs.next()) {

					Integer newNo = rs.getInt(1);
					return new Board(newNo, board.getWriter(), board.getTitle(), board.getRegDate(),
							board.getModifiedDate(), board.getCategory(), board.getAddress(), board.getArea(),
							board.getStartDate(), board.getEndDate(), board.getBudget(), board.getPart(),
							board.getRequire(), board.getImage(), board.getView_count());
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from board"); // board�� ��� ���� count
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	
	public List<Board> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			  pstmt = conn.
			  prepareStatement("SELECT boardNo, id, nickname, memNo, title, regDate, modifiedDate, category, address, area, startDate, endDate, budget, part, require, image, view_count FROM "
			  +"(SELECT ROWNUM RNUM, boardNo, id, nickname, memNo, title, regDate, modifiedDate, category, address, area, startDate, endDate, budget, part, require, image, view_count FROM "
			  +"(SELECT * FROM board ORDER BY boardNo DESC)) "
			  +"WHERE RNUM BETWEEN ? AND ?"); 
			  
			  //(MYSQL�� ��� limit���)
			  //(����Ŭ - ROWNUM���) boardNo DESC�� ������ ����   
			  //���ϵ� �� 1, 10 -> BETWEEN 1 AND 10 -> 1������
			  	  	 //11,20 -> BETWEEN 11 AND 20 -> 2������
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Board> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertBoard(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}


	private Board convertBoard(ResultSet rs) throws SQLException {
		 return new Board(rs.getInt("boardNo"), new Writer(rs.getString("id"),rs.getString("nickname"),rs.getInt("memNo")), rs.getString("title"),
				 			toDate(rs.getTimestamp("regDate")),toDate(rs.getTimestamp("modifiedDate")),rs.getString("category"),rs.getString("address"),
				 			rs.getInt("area"),rs.getString("startDate"),rs.getString("endDate"),rs.getString("budget"),rs.getString("part"),
				 			rs.getString("require"),rs.getString("image"),rs.getInt("view_count"));
		 
	}


	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	public Board selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from board where boardNo = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Board board = null;
			if (rs.next()) {
				board = convertBoard(rs);
			}
			return board;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void increaseReadCount(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update board set view_count = view_count + 1 " + "where boardNo = ?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}

	
	public int update(Connection conn, int no, String title, String category, String address, int area
			, String startDate, String endDate, String budget, String part, String require, String image) 
			throws SQLException {
		try (
				PreparedStatement pstmt = conn.prepareStatement(
				"update board set title =? ,category = ? ,address = ?,area = ?,startDate = ?,endDate = ?,"
				+ "budget = ?,part = ?,require = ?,image = ?,modifiedDate = ? "
				+ "where boardNo = ?"
				)) {
			Date nowDate = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			String strNowDate = simpleDateFormat.format(nowDate); 
			pstmt.setString(1, title);
			pstmt.setString(2, category);
			pstmt.setString(3, address);
			pstmt.setInt(4, area);
			pstmt.setString(5, startDate);
			pstmt.setString(6, endDate);
			pstmt.setString(7, budget);
			pstmt.setString(8, part);
			pstmt.setString(9, require);
			pstmt.setString(10, image);
			pstmt.setString(10, image);
			pstmt.setString(11, strNowDate);
			pstmt.setInt(12, no);
				
			
			
			return pstmt.executeUpdate();
		}
	}

	public int delete(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("delete from board where boardNo = ?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
}
