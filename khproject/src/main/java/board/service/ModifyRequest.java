package board.service;

import java.util.Map;

public class ModifyRequest {

//	private String userId;
	private int boardNo;
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

	public ModifyRequest(/*String userId,*/ int boardNo, String title, String category, String address, int area,
			String startDate, String endDate, String budget, String part, String require, String image) {
//		this.userId = userId;
		this.boardNo = boardNo;
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


//	public String getUserId() {
//		return userId;
//	}



	public int getBoardNo() {
		return boardNo;
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

}
