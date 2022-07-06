package board.model;

import java.util.Date;

public class Board {


	private Integer boardNo;
	private Writer writer;
	private String title;
	private Date regDate;
	private Date modifiedDate;
	private String category;
	private String address;
	private int area;
	private String startDate;
	private String endDate;
	private String budget;
	private String part;
	private String require;
	private String image;
	private int view_count;
	
	public Board(Integer boardNo, Writer writer, String title, Date regDate, Date modifiedDate,
			String category, String address, int area, String startDate, String endDate, String budget, String part,
			String require, String image, int view_count) {
		this.boardNo = boardNo;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.category = category;
		this.address = address;
		this.area = area;
		this.startDate = startDate;
		this.endDate = endDate;
		this.budget = budget;
		this.part = part;
		this.require = require;
		this.image = image;
		this.view_count = view_count;
	}
	
	public Integer getBoardNo() {
		return boardNo;
	}
	public Writer getWriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	public Date getRegDate() {
		return regDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
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
	public int getView_count() {
		return view_count;
	}
	
	
	
}
