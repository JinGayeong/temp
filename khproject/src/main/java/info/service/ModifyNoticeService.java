package info.service;

import java.sql.Connection;
import java.sql.SQLException;

import info.dao.NoticeContentDao;
import info.dao.NoticeDao;
import info.model.Notice;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyNoticeService {

	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();

	public void modify(ModifyNoticeRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Notice notice = noticeDao.selectById(conn, 
					modReq.getNoticeNo());
			if (notice == null) {
				throw new ArticleNotFoundException();
			}
			//if (!canModify(modReq.getNickname(), qna)) {
			//	throw new PermissionDeniedException();
			//}
			noticeDao.update(conn, 
					modReq.getNoticeNo(), modReq.getTitle(), modReq.getCategory());
			contentDao.update(conn, 
					modReq.getNoticeNo(), modReq.getContent());
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

	private boolean canModify(String nickname, Notice notice) {
		return notice.getWriter().getNickname().equals(nickname);
	}
}
