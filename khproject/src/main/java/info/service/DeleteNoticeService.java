package info.service;

import java.sql.Connection;
import java.sql.SQLException;


import auth.service.User;
import info.dao.NoticeContentDao;
import info.dao.NoticeDao;
import info.model.Notice;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteNoticeService {

	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();

	public void delete(int noticeNo, User authUser) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Notice notice = noticeDao.selectById(conn, noticeNo);
			if (notice == null) {
				throw new ArticleNotFoundException();
			}
			if (!canModify(authUser.getNickname(), notice)) {
				throw new PermissionDeniedException();
			}
			noticeDao.delete(conn, noticeNo);
			contentDao.delete(conn, noticeNo);
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

	private boolean canModify(String modfyingUserId, Notice notice) {
		return notice.getWriter().getNickname().equals(modfyingUserId);
	}
}
