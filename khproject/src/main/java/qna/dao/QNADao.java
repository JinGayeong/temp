package qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import qna.model.QNA;
import qna.model.Writer;

import java.sql.SQLException;
import java.sql.Statement;
import jdbc.JdbcUtil;

public class QNADao {

	public QNA insert(Connection conn, QNA qna) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into QNA "
					+ "(qnaNo, memNo, nickname, qnaTitle, qnaDate, answerDate, status, category) "
					+ "values (qna_ID.nextval,?,?,?,?,?,0,?)");
			pstmt.setInt(1, qna.getWriter().getMemNo());
			pstmt.setString(2, qna.getWriter().getNickname());
			pstmt.setString(3, qna.getQnaTitle());
			pstmt.setTimestamp(4, toTimestamp(qna.getQnaDate()));
			pstmt.setTimestamp(5, toTimestamp(qna.getAnswerDate()));
			pstmt.setString(6, qna.getCategory());
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM(SELECT  * FROM qna ORDER BY ROWNUM DESC)WHERE ROWNUM = 1");
				if (rs.next()) {
					Integer newNo = rs.getInt(1);
					return new QNA(newNo,
							qna.getWriter(),							
							qna.getQnaTitle(),							
							qna.getQnaDate(),
							qna.getAnswerDate(),
							"0"	,
							qna.getCategory()
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

	public int selectCount(Connection conn, String nickname, int rank) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			if(rank == 1 || rank == 2) {
				pstmt = conn.prepareStatement("select count(*) from qna where nickname = ?");
				pstmt.setString(1, nickname);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			} else {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select count(*) from qna");
				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}	
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

		public List<QNA> select(Connection conn, String nickname, int startRow, int size, int rank) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if(rank == 1 || rank ==2) {
				pstmt = conn.prepareStatement("SELECT * FROM ( "
						+ "SELECT ROWNUM AS RNUM, Z.* FROM ( "
						+ "SELECT * FROM qna where nickname = ? ORDER BY ROWNUM DESC"
						+ ") Z WHERE ROWNUM <= ? "
						+ ") WHERE RNUM >= ?");
				pstmt.setString(1, nickname);
				pstmt.setInt(2, size);
				pstmt.setInt(3, startRow);
				rs = pstmt.executeQuery();
				List<QNA> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertinfo(rs));
				}
				return result;					
			}else{
				pstmt = conn.prepareStatement("SELECT * FROM ( "
						+ "SELECT ROWNUM AS RNUM, Z.* FROM ( "
						+ "SELECT * FROM qna ORDER BY ROWNUM DESC"
						+ ") Z WHERE ROWNUM <= ? "
						+ ") WHERE RNUM >= ?");
				pstmt.setInt(1, size);
				pstmt.setInt(2, startRow);
				rs = pstmt.executeQuery();
				List<QNA> result = new ArrayList<>();
				while (rs.next()) {
					result.add(convertinfo(rs));
				}
				return result;			
			}
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private QNA convertinfo(ResultSet rs) throws SQLException {
		return new QNA(rs.getInt("qnaNo"),							
				new Writer(
						rs.getInt("memNo"),
						rs.getString("nickname")),
				rs.getString("qnaTitle"),			
		toDate(rs.getTimestamp("qnaDate")),
		toDate(rs.getTimestamp("answerDate")),
				rs.getString("status"),
				rs.getString("category"));
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	public QNA selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from qna where qnaNo = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			QNA qna = null;
			if (rs.next()) {
				qna = convertinfo(rs);
			}
			return qna;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
	
	public int delete(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from qna where qnaNo = ?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}


public int update(Connection conn, int qnaNo, String qnaTitle, String category) 
		throws SQLException {
	try (
			PreparedStatement pstmt = conn.prepareStatement(
			"update qna set qnaTitle = ?, category = ?, qnaDate = ?"
			+ "where qnaNo = ?"
			)) {
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String strNowDate = simpleDateFormat.format(nowDate); 
		pstmt.setString(1, qnaTitle);
		pstmt.setString(2, category);
		pstmt.setString(3, strNowDate);
		pstmt.setInt(4, qnaNo);
			
		
		
		return pstmt.executeUpdate();
	}
}

public int answer(Connection conn, int qnaNo) 
		throws SQLException {
	try (
			PreparedStatement pstmt = conn.prepareStatement(
			"update qna set qnaDate = ?, status = 1"
			+ "where qnaNo = ?"
			)) {
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd"); 
		String strNowDate = simpleDateFormat.format(nowDate); 
		pstmt.setString(1, strNowDate);
		pstmt.setInt(2, qnaNo);
			
		
		
		return pstmt.executeUpdate();
	}
}


}