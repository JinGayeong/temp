package board.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import board.dao.BoardDao;
import board.model.Board;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteBoardService {

	private BoardDao boardDao = new BoardDao();
	//private ArticleContentDao contentDao = new ArticleContentDao();

//	public void delete(int boardNumber, User authUser) {
//		Connection conn = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			conn.setAutoCommit(false);
//			
//			Board board = boardDao.selectById(conn, boardNumber);
//			if (board == null) {
//				throw new BoardNotFoundException();
//			}
//			if (!canModify(authUser.getId(), board)) {
//				throw new PermissionDeniedException();
//			}
//			boardDao.delete(conn, boardNumber);
//			/* contentDao.delete(conn, articleNumber); */
//			conn.commit();
//		} catch (SQLException e) {
//			JdbcUtil.rollback(conn);
//			throw new RuntimeException(e);
//		} catch (PermissionDeniedException e) {
//			JdbcUtil.rollback(conn);
//			throw e;
//		} finally {
//			JdbcUtil.close(conn);
//		}
//	}

	///////////////로그인 적용x ver///////////////////////
	public void delete(int boardNumber, User authUser) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Board board = boardDao.selectById(conn, boardNumber);
			if (board == null) {
				throw new BoardNotFoundException();
			}
//			if (!canModify(authUser.getId(), board)) {
//				throw new PermissionDeniedException();
//			}
			boardDao.delete(conn, boardNumber);
			/* contentDao.delete(conn, articleNumber); */
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
