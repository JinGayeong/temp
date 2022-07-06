package qna.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.Writer;
import qna.service.WriteQNAService;
import qna.service.WriteQNARequest;
import auth.service.User;
import mvc.command.CommandHandler;

public class WriteQNAHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/writeQNAForm.jsp";
	private WriteQNAService writeService = new WriteQNAService();
	
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
		WriteQNARequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int qna = writeService.write(writeReq);
		req.setAttribute("qna", qna);
		
		
		return "/WEB-INF/view/SuccessQNAForm.jsp";
	}

	private WriteQNARequest createWriteRequest(User user, HttpServletRequest req) {		
		return new WriteQNARequest(
				new Writer(Integer.parseInt(user.getMemno()), user.getNickname()),
				req.getParameter("qnaTitle"),
				req.getParameter("qnaContent"),
				req.getParameter("qnaDate"),
				req.getParameter("answerDate"),
				req.getParameter("status"),
				req.getParameter("category"));
	}
	
}
