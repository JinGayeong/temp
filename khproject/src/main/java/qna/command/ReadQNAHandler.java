package qna.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.service.ArticleContentNotFoundException;
import info.service.ArticleNotFoundException;
import info.service.ReadArticleService;
import mvc.command.CommandHandler;
import qna.service.QNAData;
import qna.service.ReadQNAService;

public class ReadQNAHandler implements CommandHandler {

	private ReadQNAService readService = new ReadQNAService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String noVal = req.getParameter("no");
		int articleNum = Integer.parseInt(noVal);
		try {
			QNAData qnaData = readService.getData(articleNum, true);
			req.setAttribute("qnaData", qnaData);
			return "/WEB-INF/view/readQNA.jsp";
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
