package qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import qna.model.QNAContent;
import jdbc.JdbcUtil;

public class QNAContentDao {

	public QNAContent insert(Connection conn, QNAContent content) 
	throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"insert into QNAContent (qnaNo, qnaContent, answerContent) values (?,?,null)");
			pstmt.setInt(1, content.getQnaNo());
			pstmt.setString(2, content.getQnaContent());
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
	
	public QNAContent selectById(Connection conn, int qnaNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from qnaContent where qnaNo = ?");
			pstmt.setInt(1, qnaNo);
			rs = pstmt.executeQuery();
			QNAContent content = null;
			if (rs.next()) {
				content = new QNAContent(
						rs.getInt("qnaNo"),
						rs.getString("qnaContent"),
						rs.getString("answerContent"));
			}
			return content;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int update(Connection conn, int qnaNo, String qnaContent) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update qnaContent set qnaContent = ? where qnaNo = ?")) {
			pstmt.setString(1, qnaContent);
			pstmt.setInt(2, qnaNo);
			return pstmt.executeUpdate();
		}
	}
	
	public int answer(Connection conn, int qnaNo, String answerContent) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update qnaContent set answerContent = ? where qnaNo = ?")) {
			pstmt.setString(1, answerContent);
			pstmt.setInt(2, qnaNo);
			return pstmt.executeUpdate();
		}
	}
	
	public int delete(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from qnaContent where qnaNo = ?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
}
