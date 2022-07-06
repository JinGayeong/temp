package info.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import info.model.NoticeContent;
import jdbc.JdbcUtil;

public class NoticeContentDao {

	public NoticeContent insert(Connection conn, NoticeContent content) 
	throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"insert into noticeContent (noticeNo, noticeContent) values (?,?)");
			pstmt.setInt(1, content.getNoticeNo());
			pstmt.setString(2, content.getNoticeContent());
			int insertedCount = pstmt.executeUpdate();
			if (insertedCount > 0) {
				return content;
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public NoticeContent selectById(Connection conn, int noticeNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from noticeContent where noticeNo = ?");
			pstmt.setInt(1, noticeNo);
			rs = pstmt.executeQuery();
			NoticeContent content = null;
			if (rs.next()) {
				content = new NoticeContent(
						rs.getInt("noticeNo"), rs.getString("noticeContent"));
			}
			return content;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int update(Connection conn, int noticeNo, String noticeContent) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update noticeContent set noticeContent = ? where noticeNo = ?")) {
			pstmt.setString(1, noticeContent);
			pstmt.setInt(2, noticeNo);
			return pstmt.executeUpdate();
		}
	}
	
	public int delete(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from noticeContent where noticeNo = ?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
}
