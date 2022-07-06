package qna.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import qna.dao.QNADao;
import qna.model.QNA;
import jdbc.connection.ConnectionProvider;

public class ListQNAService {

	private QNADao qnaDao = new QNADao();
	private int size = 5;

	public QNAPage getArticlePage(int pageNum, String nickname, int rank, int total) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			List<QNA> content = qnaDao.select(
					conn, nickname, ((pageNum - 1) * size)+1, (size * pageNum), rank);
			return new QNAPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
