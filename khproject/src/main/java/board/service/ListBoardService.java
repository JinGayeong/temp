package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.dao.BoardDao;
import board.model.Board;
import jdbc.connection.ConnectionProvider;

public class ListBoardService {

	private BoardDao boardDao = new BoardDao();
	private int size = 10;  //한 페이지에 보여지는 게시글 수 

	public BoardPage getBoardPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = boardDao.selectCount(conn);  //article의 모든 글을 count
			List<Board> content = boardDao.select(
					conn, (pageNum - 1) * size + 1, pageNum * size);  // 페이징처리(list에서 게시글을 10개씩 볼 수 있도록)
			return new BoardPage(total, pageNum, size, content);	// 1페이지 -> 1 , 10
		} catch (SQLException e) {									// 2페이지 -> 11, 20
			throw new RuntimeException(e);							// 3페이지 -> 21, 30  으로 리턴한다
		}
	}
}
