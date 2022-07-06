package qna.model;

import java.util.Date;

import qna.model.Writer;

public class QNA {
	
	private Integer qnaNo;
	private Writer writer;
	private String qnaTitle;	
	private Date qnaDate;
	private Date answerDate;
	private String status;
	private String category;
	
	public QNA(Integer qnaNo, Writer writer, String qnaTitle, Date qnaDate, Date answerDate, String status, String category){
		this.qnaNo = qnaNo;
		this.writer = writer;
		this.qnaTitle = qnaTitle;
		this.qnaDate = qnaDate;
		this.answerDate = answerDate;
		this.status = status;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public Integer getQnaNo() {
		return qnaNo;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public Date getQnaDate() {
		return qnaDate;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public String getStatus() {
		return status;
	}

	
	
	
	
	
	
}
