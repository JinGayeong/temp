package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

//import board.model.BoardContent;
//import board.dao.ArticleContentDao;
import board.dao.BoardDao;
import board.model.Board;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteBoardService {

	private BoardDao boardDao = new BoardDao();

	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Board board = toBoard(req); // Board 객체
			Board savedBoard = boardDao.insert(conn, board); // 삽입된 내용을 가져와 저장
			if (savedBoard == null) {
				throw new RuntimeException("fail to insert article");
			}
			/*
			 * ArticleContent content = new ArticleContent( //ArticleContent 객체
			 * savedArticle.getNumber(), req.getContent()); ArticleContent savedContent =
			 * contentDao.insert(conn, content); if (savedContent == null) { throw new
			 * RuntimeException("fail to insert article_content"); }
			 */

			conn.commit();

			return savedBoard.getBoardNo();
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

	private Board toBoard(WriteRequest req) {
		Date now = new Date();
		Integer i = 1;
	
		return new Board(i, req.getWriter(), req.getTitle(), now, now, req.getCategory(), req.getAddress(),
				req.getArea(), req.getStartDate(), req.getEndDate(), req.getBudget(), req.getPart(), req.getRequire(),
				req.getImage(),0);
	}
}
