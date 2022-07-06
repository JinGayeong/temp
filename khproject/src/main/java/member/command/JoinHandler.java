package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	private JoinService joinService = new JoinService();

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
		JoinRequest joinReq = new JoinRequest();
		
		joinReq.setNickname(req.getParameter("nickname"));
		joinReq.setId(req.getParameter("id"));
		joinReq.setPhonenum(req.getParameter("phonenum"));
		joinReq.setEmail(req.getParameter("email"));
		joinReq.setPw(req.getParameter("pw"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setAddress(req.getParameter("address"));
		joinReq.setCompanyno(req.getParameter("companyno"));
		joinReq.setBirth(req.getParameter("birth"));
		joinReq.setMemimg(req.getParameter("memimg"));
		joinReq.setRank(req.getParameter("rank"));
		joinReq.setConfirmPw(req.getParameter("confirmPw"));

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		joinReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			joinService.join(joinReq);
			return "/WEB-INF/view/joinSuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
