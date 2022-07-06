package info.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import auth.service.User;
import info.service.ArticleNotFoundException;
import info.service.DeleteNoticeService;
import info.service.PermissionDeniedException;
import mvc.command.CommandHandler;

public class DeleteNoticeHandler implements CommandHandler {
	
	private DeleteNoticeService deleteService = new DeleteNoticeService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
	
		try {
			deleteService.delete(no, authUser);
			return "/WEB-INF/view/noticeDeleteSuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
