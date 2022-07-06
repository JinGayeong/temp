package board.service;

import java.sql.Connection;
import java.sql.SQLException;

//import board.dao.BoardContentDao;
import board.dao.BoardDao;
import board.model.Board;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyBoardService {

	private BoardDao boardDao = new BoardDao();
	//private ArticleContentDao contentDao = new ArticleContentDao();

	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Board board = boardDao.selectById(conn, 
					modReq.getBoardNo());
			if (board == null) {
				throw new BoardNotFoundException();
			}
//			if (!canModify(modReq.getUserId(), board)) {
//				throw new PermissionDeniedException();
//			}
			boardDao.update(conn, 
					modReq.getBoardNo(), modReq.getTitle(),modReq.getCategory()
					,modReq.getAddress(),modReq.getArea(),modReq.getStartDate(),modReq.getEndDate()
					,modReq.getBudget(),modReq.getPart(),modReq.getRequire(),modReq.getImage()
					);
//			contentDao.update(conn, 
//					modReq.getArticleNumber(), modReq.getContent());
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

	private boolean canModify(String modfyingUserId, Board board) {
		return board.getWriter().getId().equals(modfyingUserId);
	}
}
