package info.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import info.dao.NoticeDao;
import info.service.ListNoticeService;
import info.service.NoticePage;
import jdbc.connection.ConnectionProvider;

import mvc.command.CommandHandler;

public class NoticeHandler implements CommandHandler {
	
	private ListNoticeService listService = new ListNoticeService();
	private NoticeDao noticeDao = new NoticeDao();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
		throws Exception {
			
			User user = (User) req.getSession().getAttribute("authUser");
			try(Connection conn = ConnectionProvider.getConnection()) {
			
			int total = noticeDao.selectCount(conn);
			
			String pageNoVal = req.getParameter("pageNo");
			int pageNo = 1;
			if (pageNoVal != null) {
				pageNo = Integer.parseInt(pageNoVal);
			}
			
			
			NoticePage noticePage = listService.getArticlePage(pageNo, total);
			req.setAttribute("noticePage", noticePage);
		
		return "/WEB-INF/view/notice.jsp";
		
			}catch(NullPointerException e) {
				return "/WEB-INF/view/notice.jsp";
			}
			
	}

	
}
	