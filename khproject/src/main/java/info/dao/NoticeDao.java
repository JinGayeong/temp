package info.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import info.model.Notice;
import info.model.Writer;

import java.util.Date;


import java.sql.SQLException;
import java.sql.Statement;
import jdbc.JdbcUtil;

public class NoticeDao {

	public Notice insert(Connection conn, Notice notice) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into Notice "
					+ "(noticeNo, memNo, nickname, noticeTitle, qnadate) "
					+ "values (seq_notice_no.nextval,?,?,?,?)");
			pstmt.setInt(1, notice.getWriter().getMemNo());
			pstmt.setString(2, notice.getWriter().getNickname());
			pstmt.setString(3, notice.getNoticeTitle());
			pstmt.setTimestamp(4, toTimestamp(notice.getDate()));
			
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM(SELECT  * FROM notice ORDER BY ROWNUM DESC)WHERE ROWNUM = 1");
				if (rs.next()) {
					Integer newNo = rs.getInt(1);
					return new Notice(newNo,
							notice.getWriter(),							
							notice.getNoticeTitle(),							
							notice.getDate()							
							);
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
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select count(*) from Notice");
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

		public List<Notice> select(Connection conn, int startRow, int size) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
				pstmt = conn.prepareStatement("SELECT * FROM ( "
						+ "SELECT ROWNUM AS RNUM, Z.* FROM ( "
						+ "SELECT * FROM notice ORDER BY ROWNUM DESC"
						+ ") Z WHERE ROWNUM <= ? "
						+ ") WHERE RNUM >= ?");
				pstmt.setInt(1, size);
				pstmt.setInt(2, startRow);
				rs = pstmt.executeQuery();
				List<Notice> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertinfo(rs));
				}
				return result;			
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Notice convertinfo(ResultSet rs) throws SQLException {
		return new Notice(rs.getInt("noticeNo"),							
				new Writer(
						rs.getInt("memNo"),
						rs.getString("nickname")),
				rs.getString("noticeTitle"),			
		toDate(rs.getTimestamp("qnadate"))
				);
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	public Notice selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from notice where noticeNo = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Notice notice = null;
			if (rs.next()) {
				notice = convertinfo(rs);
			}
			return notice;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
	
	public int delete(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from notice where noticeNo = ?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}


public int update(Connection conn, int noticeNo, String noticeTitle, String category) 
		throws SQLException {
	try (
			PreparedStatement pstmt = conn.prepareStatement(
			"update notice set noticeTitle = ?, category = ?, date = ?"
			+ "where noticeNo = ?"
			)) {
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String strNowDate = simpleDateFormat.format(nowDate); 
		pstmt.setString(1, noticeTitle);
		pstmt.setString(2, category);
		pstmt.setString(3, strNowDate);
		pstmt.setInt(4, noticeNo);
			
		
		
		return pstmt.executeUpdate();
	}
}


}