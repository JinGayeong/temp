package board.service;

import java.util.Map;

import board.model.Writer;

public class WriteRequest {

	private Writer writer;
	private String title;
	private String category;
	private String address;
	private int area;
	private String startDate;
	private String endDate;
	private String budget;
	private String part;
	private String require;
	private String image;
	


	public WriteRequest(Writer writer, String title, String category, String address, int area, String startDate,
			String endDate, String budget, String part, String require, String image) {
		super();
		this.writer = writer;
		this.title = title;
		this.category = category;
		this.address = address;
		this.area = area;
		this.startDate = startDate;
		this.endDate = endDate;
		this.budget = budget;
		this.part = part;
		this.require = require;
		this.image = image;
	}




	public Writer getWriter() {
		return writer;
	}




	public String getTitle() {
		return title;
	}




	public String getCategory() {
		return category;
	}




	public String getAddress() {
		return address;
	}




	public int getArea() {
		return area;
	}




	public String getStartDate() {
		return startDate;
	}




	public String getEndDate() {
		return endDate;
	}




	public String getBudget() {
		return budget;
	}




	public String getPart() {
		return part;
	}




	public String getRequire() {
		return require;
	}




	public String getImage() {
		return image;
	}




	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}




	@Override
	public String toString() {
		return "WriteRequest [writer=" + writer + ", title=" + title + ", category=" + category + ", address=" + address
				+ ", area=" + area + ", startDate=" + startDate + ", endDate=" + endDate + ", budget=" + budget
				+ ", part=" + part + ", require=" + require + ", image=" + image + "]";
	}
	
	
	
}