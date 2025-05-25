package com.example.demo.model;

public class TicketInfo {

	private String date;
	private String title;
	private String place;

	public TicketInfo(String date, String title, String place) {
		this.date = date;
		this.title = title;
		this.place = place;
	}

	public TicketInfo() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String venue) {
		place = venue;
	}

}
