package info.service;

import info.model.Notice;
import info.model.NoticeContent;

public class NoticeData {

	private Notice notice;
	private NoticeContent content;

	public NoticeData(Notice notice, NoticeContent content) {
		this.notice = notice;
		this.content = content;
	}

	public Notice getNotice() {
		return notice;
	}

	public String getContent() {
		return content.getNoticeContent();
	}

}
