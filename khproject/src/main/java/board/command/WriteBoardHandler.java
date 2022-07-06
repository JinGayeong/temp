package board.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import auth.service.User;
import board.model.TblAttach;
import board.model.Writer;
import board.service.WriteBoardService;
import board.service.WriteRequest;
import mvc.command.CommandHandler;

public class WriteBoardHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/WriteBoardForm.jsp";
	private WriteBoardService writeService = new WriteBoardService();

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

		User user = (User) req.getSession(false).getAttribute("authUser"); // 濡쒓렇�씤�긽�깭

		WriteRequest writeReq = createWriteRequest(user, req);
		System.out.println("/*** WriteRequest="+writeReq.toString());

		/*
		writeReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		*/

		int newBoardNo = writeService.write(writeReq);

		req.setAttribute("newBoardNo", newBoardNo); // return savedArticle.getNumber();

		return "/WEB-INF/view/WriteBoardFormSuccess.jsp";
	}

	static boolean isStringEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}



	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		
		
		//String uploadPath = "C://Users//gytjs//git//khproject_team4//khproject//src//main//upload";
		String uploadPath = req.getRealPath("upload");
		System.out.println(uploadPath);
		int size = 10 * 1024 * 1024;	


		TblAttach tblAttach = null;
		List<TblAttach> list = new ArrayList<TblAttach>();

		try {
			MultipartRequest multi = new MultipartRequest(req, uploadPath, size, "utf-8",
					new DefaultFileRenamePolicy());

			Enumeration fileNames = multi.getFileNames();
			List<String> files = Collections.list(fileNames);

			String file,  origfilename;
			String filename = null;
			for(int i=0; i<files.size(); i++) {
				//file = (String) fileNames.nextElement();
				file = files.get(i);
				filename = multi.getFilesystemName(file);	
				origfilename = multi.getOriginalFileName(file);
				
				//System.out.println("filename="+filename);
				if(filename == null) {
					filename = "등록된 이미지가 없습니다.";
					continue;
				}
				
				tblAttach = new TblAttach(filename);
				list.add(tblAttach);
			}
			
			String address = multi.getParameter("sido1").concat(" ").concat(multi.getParameter("gugun1"));  // .concat으로 지역(시/도, 구/군) 문자열 합치고 (" ") 띄어쓰기하여 list에 출력  ex) 강원도 홍천군
			String defaultDate = "0001-01-01";
			String undefined_budget = "-1";
			String startDate = multi.getParameter("undefined_start")==null ? multi.getParameter("startDate"):defaultDate;  
			String endDate = multi.getParameter("undefined_end")==null ? multi.getParameter("endDate"):defaultDate;  
			String budget = multi.getParameter("undefined_budget")==null ? multi.getParameter("budget"):undefined_budget; 
			//0706 5시 36분 수정
			return new WriteRequest(new Writer(user.getId(), user.getNickname(), 1), multi.getParameter("title"),
					multi.getParameter("category"), address, Integer.parseInt(multi.getParameter("area")), startDate, endDate,
					budget, multi.getParameter("part"), multi.getParameter("require"),
					filename);
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
		
	}

}
