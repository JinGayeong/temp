package member.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.MypageData;
import member.service.MypageNotFoundException;
import member.service.ReadMypageService;
import mvc.command.CommandHandler;

public class ReadMypageHandler implements CommandHandler {

	private ReadMypageService readMypageService = new ReadMypageService();

	private static final String FORM_VIEW = "/WEB-INF/view/mypageMember.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 여기 ReadArticleHandler 참고함.
		try {
			// 마이페이지 서비스에서 가져오도록 만들어야한다.
			// 뇌정지가 온다 이거 세션에서 아이디값 가져오는 코드가 맞을까?
			String userId = (String) req.getSession().getAttribute("id");

			MypageData mypageData = readMypageService.getMypage(userId);

			req.setAttribute("mypageData", mypageData);
			return FORM_VIEW;
		} catch (MypageNotFoundException e) {
			/* req.getServletContext().log("no article", e); */
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		MypageRequest mypageReq = new MypageRequest();
		mypageReq.setNickname(req.getParameter("nickname"));
		mypageReq.setAddress(req.getParameter("address"));

		// 개인정보 수정시 패스워드 확인 메소드 들어가야하니깐
		mypageReq.setConfirmPassword(req.getParameter("confirmPassword"));

		Map<String, Boolean> errors = new HashMap<>();
		// 리퀘스트 받은걸 errors에 넣는 과정
		req.setAttribute("errors", errors);

		mypageReq.validate(errors);

		// 해쉬맵은 isEmpty 쓰면 레코드 값이 있으면 false, 없으면 true 반환
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			ReadMypageSerive.join(mypageReq);
			return "/WEB-INF/view/mypageMemberUpateSuccess.jsp";
		} catch (DuplicateIdException e) {
			// 여기 duplicateId 에서 수정했는데 나중에 오류나면 바꿔야됨.
			// 닉네임 중복 체크 에러 검증 부분
			// service 에 DulicateNicknameException.java 파일 넣어봄.
			errors.put("duplicateNickname", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
