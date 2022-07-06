package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import board.service.BoardNotFoundException;
import board.service.BoardData;
import board.service.BoardNotFoundException;
import board.service.ReadBoardService;
import board.model.TblAttachList;
import mvc.command.CommandHandler;
import board.service.FileService;

public class ReadBoardHandler implements CommandHandler {

	private ReadBoardService readService = new ReadBoardService();
	private FileService fileService = new FileService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String noVal = req.getParameter("no");
		int boardNum = Integer.parseInt(noVal);

		try {
			BoardData boardData = readService.getBoard(boardNum, true);

			TblAttachList tblAttachList = fileService.view(boardNum);
			req.getSession().setAttribute("tblAttachList", tblAttachList);
			
			req.setAttribute("boardData", boardData);
			return "/WEB-INF/view/ReadBoardForm.jsp";
		} catch (BoardNotFoundException e) {
			req.getServletContext().log("no board", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
