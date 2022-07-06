package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardPage;
import board.service.ListBoardService;
import mvc.command.CommandHandler;

public class ListBoardHandler implements CommandHandler {

	private ListBoardService listService = new ListBoardService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		BoardPage boardPage = listService.getBoardPage(pageNo);
		req.setAttribute("boardPage", boardPage);
		return "/WEB-INF/view/listBoardForm.jsp";
	}

}
