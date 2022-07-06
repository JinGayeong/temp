package info.service;

import java.sql.Connection;
import java.sql.SQLException;

import info.dao.NoticeContentDao;
import info.dao.NoticeDao;
import info.model.Notice;
import info.model.NoticeContent;
import jdbc.connection.ConnectionProvider;

public class ReadNoticeService {

	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	
	public NoticeData getData(int articleNum, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()){
			Notice notice = noticeDao.selectById(conn, articleNum);
			if (notice == null) {
				throw new ArticleNotFoundException();
			}
			NoticeContent content = contentDao.selectById(conn, articleNum);
			if (content == null) {
				throw new ArticleContentNotFoundException();
			}
				return new NoticeData(notice, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
