package info.model;

import java.util.Date;



public class Notice {
	
	private Integer noticeNo;
	private Writer writer;
	private String noticeTitle;	
	private Date date;
	
	
	public Notice(Integer noticeNo, Writer writer, String noticeTitle, Date date){
		this.noticeNo = noticeNo;
		this.writer = writer;
		this.noticeTitle = noticeTitle;
		this.date = date;
		

	}


	public Writer getWriter() {
		return writer;
	}


	public Integer getNoticeNo() {
		return noticeNo;
	}


	public String getNoticeTitle() {
		return noticeTitle;
	}


	public Date getDate() {
		return date;
	}


	
	
	
	
	
}
