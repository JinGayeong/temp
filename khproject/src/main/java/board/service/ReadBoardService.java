package board.service;

import java.sql.Connection;
import java.sql.SQLException;

//import board.dao.ArticleContentDao;
import board.dao.BoardDao;
//import board.model.ArticleContent;
import board.model.Board;
import jdbc.connection.ConnectionProvider;

public class ReadBoardService {

	private BoardDao boardDao = new BoardDao();
	//private ArticleContentDao contentDao = new ArticleContentDao();
	
	public BoardData getBoard(int boardNum, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()){
			Board board = boardDao.selectById(conn, boardNum);
			if (board == null) {
				throw new BoardNotFoundException();
			}
			Board content = boardDao.selectById(conn, boardNum);
			if (content == null) {
				throw new ArticleContentNotFoundException();
			}
			if (increaseReadCount) {
				boardDao.increaseReadCount(conn, boardNum);
			}
			return new BoardData(board);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
