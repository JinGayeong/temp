package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePasswordRequest;
import member.service.ChangePasswordService;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class ChangePasswordHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/myPageMember.jsp";
	private ChangePasswordService changePwdSvc = new ChangePasswordService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
	throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("/*** ChangePasswordHandler.processForm() 실행");
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
	throws Exception {
		System.out.println("/*** ChangePasswordHandler.processSubmit() 실행");
		User user = (User)req.getSession().getAttribute("authUser");
		ChangePasswordRequest changeReq = new ChangePasswordRequest();
			
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		String newRank = req.getParameter("newRank");
		String newCompanyno = req.getParameter("newCompanyno");
		String newId = req.getParameter("newId");
		String newNickname = req.getParameter("newNickname");
		String newPw = req.getParameter("newPw");
//		String confirmNewPw = req.getParameter("confirmNewPw");
		String newMemimg = req.getParameter("newMemimg");
		String newName = req.getParameter("newName");
		String newBirth = req.getParameter("newBirth");
		String newAddress = req.getParameter("newAddress");
		String newPhonenum = req.getParameter("newPhonenum");
		String newEmail = req.getParameter("newEmail");

		changeReq.validate(errors);

		if (newRank == null || newRank.isEmpty()) {
			errors.put("newRank", Boolean.TRUE);
		}
		if (newCompanyno == null || newCompanyno.isEmpty()) {
			errors.put("newCompanyno", Boolean.TRUE);
		}
		if (newId == null || newId.isEmpty()) {
			errors.put("newId", Boolean.TRUE);
		}
		if (newNickname == null || newNickname.isEmpty()) {
			errors.put("newNickname", Boolean.TRUE);
		}
		if (newPw == null || newPw.isEmpty()) {
			errors.put("newPw", Boolean.TRUE);
		}
//		if (confirmNewPw == null || confirmNewPw.isEmpty()) {
//			errors.put("newNickname", Boolean.TRUE);
//		}
		if (newMemimg == null || newMemimg.isEmpty()) {
			errors.put("newMemimg", Boolean.TRUE);
		}
		if (newName == null || newName.isEmpty()) {
			errors.put("newName", Boolean.TRUE);
		}
		if (newBirth == null || newBirth.isEmpty()) {
			errors.put("newBirth", Boolean.TRUE);
		}
		if (newAddress == null || newAddress.isEmpty()) {
			errors.put("newAddress", Boolean.TRUE);
		}
		if (newPhonenum == null || newPhonenum.isEmpty()) {
			errors.put("newPhonenum", Boolean.TRUE);
		}
		if (newEmail == null || newEmail.isEmpty()) {
			errors.put("newEmail", Boolean.TRUE);
		}
		if (!errors.isEmpty()) {
			//return FORM_VIEW;
		}
		
		try {
			changePwdSvc.changePassword(
					user.getId(), newRank, newCompanyno, newId, newNickname, newPw,
					newMemimg, newName, newBirth, newAddress, newPhonenum, newEmail);
			return "/WEB-INF/view/changePwdSuccess.jsp";
		} catch (MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
}
