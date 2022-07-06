package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import qna.dao.QNAContentDao;
import qna.dao.QNADao;
import qna.model.QNA;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyQNAService {

	private QNADao qnaDao = new QNADao();
	private QNAContentDao contentDao = new QNAContentDao();

	public void modify(ModifyQNARequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			QNA qna = qnaDao.selectById(conn, 
					modReq.getQnaNo());
			if (qna == null) {
				throw new ArticleNotFoundException();
			}
			//if (!canModify(modReq.getNickname(), qna)) {
			//	throw new PermissionDeniedException();
			//}
			qnaDao.update(conn, 
					modReq.getQnaNo(), modReq.getTitle(), modReq.getCategory());
			contentDao.update(conn, 
					modReq.getQnaNo(), modReq.getContent());
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

	private boolean canModify(String nickname, QNA qna) {
		return qna.getWriter().getNickname().equals(nickname);
	}
}
