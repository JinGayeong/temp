package info.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.service.ArticleContentNotFoundException;
import info.service.ArticleNotFoundException;
import info.service.NoticeData;
import info.service.ReadNoticeService;
import mvc.command.CommandHandler;


public class ReadNoticeHandler implements CommandHandler {

	private ReadNoticeService readService = new ReadNoticeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String noVal = req.getParameter("no");
		int articleNum = Integer.parseInt(noVal);
		try {
			NoticeData noticeData = readService.getData(articleNum, true);
			req.setAttribute("noticeData", noticeData);
			return "/WEB-INF/view/readNotice.jsp";
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
