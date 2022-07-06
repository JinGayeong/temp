package info.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import auth.service.User;
import info.model.Writer;
import info.service.WriteNoticeRequest;
import info.service.WriteNoticeService;
import mvc.command.CommandHandler;

public class WriteNoticeHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/noticeWriteForm.jsp";
	private WriteNoticeService writeService = new WriteNoticeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteNoticeRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int notice = writeService.write(writeReq);
		req.setAttribute("notice", notice);
		
		
		return "/WEB-INF/view/noticeWriteSuccess.jsp";
	}

	private WriteNoticeRequest createWriteRequest(User user, HttpServletRequest req) {		
		return new WriteNoticeRequest(
				new Writer(Integer.parseInt(user.getMemno()), user.getNickname()),
				req.getParameter("title"),
				req.getParameter("content"),
				req.getParameter("date")
				);
	}
	
}
