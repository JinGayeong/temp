package qna.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.service.ArticleNotFoundException;
import qna.service.DeleteQNAService;
import qna.service.PermissionDeniedException;
import qna.service.ReadQNAService;
import auth.service.User;
import mvc.command.CommandHandler;

public class DeleteQNAHandler implements CommandHandler {
	
	private DeleteQNAService deleteService = new DeleteQNAService();
	

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
			return "/WEB-INF/view/deleteQNASuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
