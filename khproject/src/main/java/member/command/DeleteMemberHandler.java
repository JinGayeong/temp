package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.service.BoardNotFoundException;
import board.service.PermissionDeniedException;
import member.service.DeleteMemberService;
import mvc.command.CommandHandler;

public class DeleteMemberHandler implements CommandHandler {
	
	private DeleteMemberService deleteService = new DeleteMemberService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String no = req.getParameter("no");
		/* int no = Integer.parseInt(noVal); */

		try {
			deleteService.delete(no, authUser);
			return "/WEB-INF/view/memberDeleteSuccess.jsp";
		} catch (BoardNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
