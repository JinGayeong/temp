package info.service;

import java.sql.Connection;
import java.sql.SQLException;


import info.dao.NoticeDao;
import info.model.Notice;

import jdbc.connection.ConnectionProvider;

public class ReadArticleService {

	private NoticeDao infoDao = new NoticeDao();

	
	public ArticleData getArticle(int articleNum, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()){
			Notice info = infoDao.selectById(conn, articleNum);
			if (info == null) {
				throw new ArticleNotFoundException();
			}
			Notice content = infoDao.selectById(conn, articleNum);
			if (content == null) {
				throw new ArticleContentNotFoundException();
			}
				return new ArticleData(info);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
