package board.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import board.service.BoardData;
import board.service.BoardNotFoundException;
import board.service.ModifyBoardService;
import board.service.ModifyRequest;
import board.service.PermissionDeniedException;
import board.service.ReadBoardService;
import mvc.command.CommandHandler;

public class ModifyBoardHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/modifyForm.jsp";

	private ReadBoardService readService = new ReadBoardService();
	private ModifyBoardService modifyService = new ModifyBoardService();

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
			int no = Integer.parseInt(noVal); //臾몄옄�뿴�쓣 �닽�옄�삎�쑝濡� 蹂��솚

			BoardData boardData = readService.getBoard(no, false); //count瑜� 利앷��븯吏� �븡�뒗�떎
			//User authUser = (User) req.getSession().getAttribute("authUser"); //濡쒓렇�씤 �긽�깭
			//�닔�젙�븷 �븣 濡쒓렇�씤�븳 �궗�엺�씠 = 湲� �옉�꽦�옄�씤吏� �솗�씤
//			if (!canModify(authUser, articleData)) {
//				res.sendError(HttpServletResponse.SC_FORBIDDEN);
//				return null;
//			}
			ModifyRequest modReq = new ModifyRequest(no, //ModifyRequest 媛앹껜
					boardData.getBoard().getTitle(),
					boardData.getBoard().getCategory(),
					boardData.getBoard().getAddress(),
					boardData.getBoard().getArea(),
					boardData.getBoard().getStartDate(),
					boardData.getBoard().getEndDate(),
					boardData.getBoard().getBudget(),
					boardData.getBoard().getPart(),
					boardData.getBoard().getRequire(),
					boardData.getBoard().getImage()
					);

			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (BoardNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

//	private boolean canModify(User authUser, BoardData boardData) {
//		String writerId = articleData.getArticle().getWriter().getId(); //湲� �옉�꽦�옄�쓽 �븘�씠�뵒瑜� 李얜뒗�떎
//		return authUser.getId().equals(writerId); //濡쒓렇�씤�븳 �궗�엺 �븘�씠�뵒 = 湲� �옉�꽦�옄�쓽 �븘�씠�뵒�씤吏� �솗�씤
//	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		//noVal = req.getParameter("no");
		System.out.println("noval�쓽 媛믪�"+noVal+"�엯�땲�떎2");
		int no = Integer.parseInt(noVal);
		String address = req.getParameter("sido1").concat(" ").concat(req.getParameter("gugun1"));  // .concat으로 지역(시/도, 구/군) 문자열 합치고 (" ") 띄어쓰기하여 list에 출력  ex) 강원도 홍천군
		String defaultDate = "0001-01-01";
		String undefined_budget = "-1";
		String startDate = req.getParameter("undefined_start")==null ? req.getParameter("startDate"):defaultDate;  
		String endDate = req.getParameter("undefined_end")==null ? req.getParameter("endDate"):defaultDate;  
		String budget = req.getParameter("undefined_budget")==null ? req.getParameter("budget"):undefined_budget; 
		
		ModifyRequest modReq = new ModifyRequest(no, //ModifyRequest 媛앹껜
				req.getParameter("title"),
				req.getParameter("category"),
				address,
				Integer.parseInt(req.getParameter("area")),
				startDate,
				endDate,
				budget,
				req.getParameter("part"),
				req.getParameter("require"),
				req.getParameter("image")
				);
		req.setAttribute("modReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
//		if (!errors.isEmpty()) {
//			return FORM_VIEW;
//		}
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/modifySuccess.jsp";
		} catch (BoardNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
