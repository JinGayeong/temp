package qna.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.service.QNAData;
import qna.service.ArticleNotFoundException;
import qna.service.ModifyQNAService;
import qna.service.ModifyQNARequest;
import qna.service.PermissionDeniedException;
import qna.service.ReadQNAService;
import auth.service.User;
import mvc.command.CommandHandler;

public class ModifyQNAHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/modifyQNAForm.jsp";

	private ReadQNAService readService = new ReadQNAService();
	private ModifyQNAService modifyService = new ModifyQNAService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	String noVal;
	private String processForm(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			QNAData qnadata = readService.getData(no, false);
			//User authUser = (User) req.getSession().getAttribute("authUser");
//			if (!canModify(authUser, qnadata)) {
//				res.sendError(HttpServletResponse.SC_FORBIDDEN);
//				return null;
//			}
			ModifyQNARequest modReq = new ModifyQNARequest(no,
					qnadata.getQna().getQnaTitle(),
					qnadata.getContent().getQnaContent(),
					qnadata.getQna().getCategory());

			req.setAttribute("modQNAReq", modReq);
			
			return FORM_VIEW;
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

//	private boolean canModify(User authUser, QNAData qnadata) {
//		int user = qnadata.getQna().getWriter().getMemNo();
//		return authUser.getMemno().equals(user);
//	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		User authUser = (User)req.getSession().getAttribute("authUser");
		//noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);

		ModifyQNARequest modReq = new ModifyQNARequest(no,
				req.getParameter("qnaTitle"),
				req.getParameter("qnaContent"),
				req.getParameter("category"));
		
		req.setAttribute("modQNAReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
//		if (!errors.isEmpty()) {
//			return FORM_VIEW;
//		}
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/modifyQNASuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
