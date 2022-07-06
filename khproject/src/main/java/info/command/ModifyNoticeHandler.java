package info.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import info.service.ArticleNotFoundException;
import info.service.ModifyNoticeRequest;
import info.service.ModifyNoticeService;
import info.service.NoticeData;
import info.service.PermissionDeniedException;
import info.service.ReadNoticeService;
import mvc.command.CommandHandler;

public class ModifyNoticeHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/modifyNoticeForm.jsp";

	private ReadNoticeService readService = new ReadNoticeService();
	private ModifyNoticeService modifyService = new ModifyNoticeService();

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
			NoticeData noticedata = readService.getData(no, false);
			//User authUser = (User) req.getSession().getAttribute("authUser");
//			if (!canModify(authUser, qnadata)) {
//				res.sendError(HttpServletResponse.SC_FORBIDDEN);
//				return null;
//			}
			ModifyNoticeRequest modReq = new ModifyNoticeRequest(no,
					noticedata.getNotice().getNoticeTitle(),
					noticedata.getContent());
						// content?
			req.setAttribute("modNoticeReq", modReq);
			
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

		ModifyNoticeRequest modReq = new ModifyNoticeRequest(no,
				req.getParameter("noticeTitle"),
				req.getParameter("noticeContent"));
		
		req.setAttribute("modNoticeReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
//		if (!errors.isEmpty()) {
//			return FORM_VIEW;
//		}
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/noticeModifySuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
