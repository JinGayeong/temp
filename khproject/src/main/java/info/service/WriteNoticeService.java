package info.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import info.dao.NoticeContentDao;
import info.dao.NoticeDao;
import info.model.Notice;
import info.model.NoticeContent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteNoticeService {

	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao noticeContentDao = new NoticeContentDao();

	public Integer write(WriteNoticeRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Notice notice = toNotice(req); 
			Notice savedNotice = noticeDao.insert(conn, notice); 
			if (savedNotice == null) {
				throw new RuntimeException("fail to insert article");
			}
			
			 NoticeContent content = new NoticeContent(
					 savedNotice.getNoticeNo(),
					 req.getNoticeContent()); 
			 NoticeContent savedContent = noticeContentDao.insert(conn, content);
			 if (savedContent == null) { 
			 throw new RuntimeException("fail to insert article_content"); 
			}		 
			conn.commit();
			return savedNotice.getNoticeNo();
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

	private Notice toNotice(WriteNoticeRequest req) {
		Date now = new Date();

		return new Notice(1, req.getWriter(), req.getNoticeTitle(), now);
	}
}
