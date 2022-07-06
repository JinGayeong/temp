package qna.service;

import java.util.Map;

import qna.model.Writer;

public class WriteQNARequest {

	private Writer writer;
	private String qnaTitle;
	private String qnacontent;
	private String qnaDate;
	private String answerDate;
	private String status;
	private String category;

	public WriteQNARequest(Writer writer, String qnaTitle, String qnacontent, String qnaDate, String answerDate, String status, String category) {
		this.writer = writer;
		this.qnaTitle = qnaTitle;
		this.qnacontent = qnacontent;
		this.qnaDate = qnaDate;
		this.answerDate = answerDate;
		this.status = status;
		this.category = category;
	}
	
	
	

	public String getCategory() {
		return category;
	}




	public Writer getWriter() {
		return writer;
	}



	public String getQnacontent() {
		return qnacontent;
	}




	public String getQnaTitle() {
		return qnaTitle;
	}



	public String getQnaDate() {
		return qnaDate;
	}



	public String getAnswerDate() {
		return answerDate;
	}



	public String getStatus() {
		return status;
	}



	public void validate(Map<String, Boolean> errors) {
		if (qnaTitle == null || qnaTitle.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}