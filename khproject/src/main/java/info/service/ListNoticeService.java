package info.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import info.dao.NoticeDao;
import info.model.Notice;
import jdbc.connection.ConnectionProvider;

public class ListNoticeService {

	private NoticeDao noticeDao = new NoticeDao();
	private int size = 5;

	public NoticePage getArticlePage(int pageNum, int total) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			List<Notice> content = noticeDao.select(
					conn, ((pageNum - 1) * size)+1, (size * pageNum));
			return new NoticePage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
