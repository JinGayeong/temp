package qna.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import jdbc.connection.ConnectionProvider;
import qna.service.QNAPage;
import qna.dao.QNADao;
import qna.service.ListQNAService;
import mvc.command.CommandHandler;

public class QNAHandler implements CommandHandler {
	
	private ListQNAService listService = new ListQNAService();
	private QNADao qnaDao = new QNADao();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
		throws Exception {
			
			User user = (User) req.getSession().getAttribute("authUser");
			try(Connection conn = ConnectionProvider.getConnection()) {
			
			int total = qnaDao.selectCount(conn, user.getNickname(), Integer.parseInt(user.getRank()));
			
			String pageNoVal = req.getParameter("pageNo");
			int pageNo = 1;
			if (pageNoVal != null) {
				pageNo = Integer.parseInt(pageNoVal);
			}
			
			
			QNAPage qnaPage = listService.getArticlePage(pageNo, user.getNickname(), Integer.parseInt(user.getRank()), total);
			req.setAttribute("qnaPage", qnaPage);
		
		return "/WEB-INF/view/qna.jsp";
		
			}catch(NullPointerException e) {
				return "/WEB-INF/view/loginForm.jsp";
				
			}
			
	}

	
}
	