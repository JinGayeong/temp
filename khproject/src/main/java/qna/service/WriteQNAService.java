package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

//import board.model.BoardContent;
//import board.dao.ArticleContentDao;
import qna.dao.QNADao;
import qna.dao.QNAContentDao;
import qna.model.QNA;
import qna.model.QNAContent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteQNAService {

	private QNADao qnaDao = new QNADao();
	private QNAContentDao qnaContentDao = new QNAContentDao();

	public Integer write(WriteQNARequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			QNA qna = toQNA(req); 
			QNA savedQNA = qnaDao.insert(conn, qna); 
			if (savedQNA == null) {
				throw new RuntimeException("fail to insert article");
			}
			
			 QNAContent content = new QNAContent(
					 savedQNA.getQnaNo(),
					 req.getQnacontent(),
					 null); 
			 QNAContent savedContent = qnaContentDao.insert(conn, content);
			 if (savedContent == null) { 
			 throw new RuntimeException("fail to insert article_content"); 
			}		 
			conn.commit();
			return savedQNA.getQnaNo();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private QNA toQNA(WriteQNARequest req) {
		Date now = new Date();

		return new QNA(null, req.getWriter(), req.getQnaTitle(), now, now, req.getStatus(), req.getCategory());
	}
}
