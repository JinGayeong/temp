package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import qna.dao.QNAContentDao;
import qna.dao.QNADao;
import qna.model.QNA;
import auth.service.User;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteQNAService {

	private QNADao qnaDao = new QNADao();
	private QNAContentDao contentDao = new QNAContentDao();

	public void delete(int qnaNo, User authUser) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			QNA qna = qnaDao.selectById(conn, qnaNo);
			if (qna == null) {
				throw new ArticleNotFoundException();
			}
			if (!canModify(authUser.getNickname(), qna)) {
				throw new PermissionDeniedException();
			}
			qnaDao.delete(conn, qnaNo);
			contentDao.delete(conn, qnaNo);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean canModify(String modfyingUserId, QNA qna) {
		return qna.getWriter().getNickname().equals(modfyingUserId);
	}
}
