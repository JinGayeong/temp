package board.command;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class FileDownHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processSubmit(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("utf-8");

		String fileName = req.getParameter("file_name");
		System.out.println("fileName=" + fileName);

		String savePath = "upload";

		ServletContext context = req.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		String sFilePath = sDownloadPath + "\\" + fileName;
		System.out.println("sFilePath=" + sFilePath);

		byte b[] = new byte[4096];
		File oFile = new File(sFilePath);

		FileInputStream in = new FileInputStream(sFilePath);

		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>" + sMimeType);

		// octet-stream�� 8鍮꾪듃濡� �맂 �씪�젴�쓽 �뜲�씠�꽣瑜� �쑜�빀�땲�떎. 吏��젙�릺吏� �븡�� �뙆�씪 �삎�떇�쓣 �쓽誘명빀�땲�떎.
		if (sMimeType == null)
			sMimeType = "application/octet-stream";

		res.setContentType(sMimeType);

		// �븳湲� �뾽濡쒕뱶 (�씠 遺�遺꾩씠 �븳湲� �뙆�씪紐낆씠 源⑥��뒗 寃껋쓣 諛⑹��빐 以띾땲�떎.)
		String sEncoding = new String(fileName.getBytes("utf-8"), "8859_1");

		res.setHeader("Content-Disposition", "attachment; filename= " + sEncoding);
		
		ServletOutputStream out2 = res.getOutputStream();
		int numRead;
		
		// 諛붿씠�듃 諛곗뿴b�쓽 0踰� 遺��꽣 numRead踰� 源뚯� 釉뚮씪�슦��濡� 異쒕젰
		while ((numRead = in.read(b, 0, b.length)) != -1) {
			out2.write(b, 0, numRead);
		}
		out2.flush();
		out2.close();
		in.close();

		return null;
	}

}
