package com.example.demo.model;

public class GroupSchedule {
	private String month;
	private String day;
	private String title;
	private String category;

	public GroupSchedule() {
	}

	public GroupSchedule(String month, String day, String title, String category) {
		this.month = month;
		this.day = day;
		this.title = title;
		this.category = category;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
