package info.service;

import java.util.Map;

import info.model.Writer;


public class WriteNoticeRequest {

	private Writer writer;
	private String noticeTitle;
	private String noticeContent;
	private String date;
	
	public WriteNoticeRequest(Writer writer, String noticeTitle, String noticeContent, String date) {
		this.writer = writer;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.date = date;
		
	}
	
	
	




	public String getNoticeTitle() {
		return noticeTitle;
	}




	public String getNoticeContent() {
		return noticeContent;
	}




	public String getDate() {
		return date;
	}




	public Writer getWriter() {
		return writer;
	}





	public void validate(Map<String, Boolean> errors) {
		if (noticeTitle == null || noticeTitle.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}