package qna.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.service.QNAData;
import qna.service.ArticleNotFoundException;
import qna.service.AnswerQNAService;
import qna.service.AnswerQNARequest;
import qna.service.PermissionDeniedException;
import qna.service.ReadQNAService;
import auth.service.User;
import mvc.command.CommandHandler;

public class AnswerQNAHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/answerQNAForm.jsp";

	private ReadQNAService readService = new ReadQNAService();
	private AnswerQNAService answerService = new AnswerQNAService();

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
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
			String qnaDate = sdf.format(qnadata.getQna().getQnaDate());
			String answerDate = sdf.format(qnadata.getQna().getAnswerDate());
			AnswerQNARequest ansReq = new AnswerQNARequest(no, qnadata.getQna().getWriter().getNickname(),
					qnadata.getQna().getQnaTitle() ,qnadata.getContent().getQnaContent(), 
					qnadata.getQna().getCategory(), qnadata.getContent().getAnswerContent(),
					qnaDate, answerDate);
			
			req.setAttribute("ansQNAReq", ansReq);
			
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

		AnswerQNARequest ansReq = new AnswerQNARequest(no,
				authUser.getNickname(),
				req.getParameter("qnaTitle"),
				req.getParameter("qnaContent"),
				req.getParameter("category"),
				req.getParameter("answerContent"),
				req.getParameter("qnaDate"),
				req.getParameter("answerDate"));
		
		req.setAttribute("ansQNAReq", ansReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		ansReq.validate(errors);
//		if (!errors.isEmpty()) {
//			return FORM_VIEW;
//		}
		try {
			answerService.Answer(ansReq);
			return "/WEB-INF/view/SuccessAnswerQNAForm.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
