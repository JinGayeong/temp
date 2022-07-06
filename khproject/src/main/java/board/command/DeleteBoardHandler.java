package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.service.BoardNotFoundException;
import board.service.DeleteBoardService;
import board.service.PermissionDeniedException;
import board.service.ReadBoardService;
import mvc.command.CommandHandler;

public class DeleteBoardHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/modifyForm.jsp";

	private ReadBoardService readService = new ReadBoardService();
	//private ModifyArticleService modifyService = new ModifyArticleService();
	private DeleteBoardService deleteService = new DeleteBoardService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		/*
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		*/
		return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);

		/*
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
				req.getParameter("title"),
				req.getParameter("content"));
		req.setAttribute("modReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		*/
		try {
			//modifyService.modify(modReq);
			//return "/WEB-INF/view/modifySuccess.jsp";
			deleteService.delete(no, authUser);
			return "/WEB-INF/view/deleteSuccess.jsp";
		} catch (BoardNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
