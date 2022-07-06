package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import qna.dao.QNAContentDao;
import qna.dao.QNADao;
import qna.model.QNA;
import qna.model.QNAContent;
import jdbc.connection.ConnectionProvider;

public class ReadQNAService {

	private QNADao qnaDao = new QNADao();
	private QNAContentDao contentDao = new QNAContentDao();
	
	public QNAData getData(int articleNum, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()){
			QNA qna = qnaDao.selectById(conn, articleNum);
			if (qna == null) {
				throw new ArticleNotFoundException();
			}
			QNAContent content = contentDao.selectById(conn, articleNum);
			if (content == null) {
				throw new ArticleContentNotFoundException();
			}
				return new QNAData(qna, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
