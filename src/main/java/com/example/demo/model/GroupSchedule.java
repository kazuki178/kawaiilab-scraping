package com.example.demo.model;


import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupSchedule {
	private String month;
	private String day;
	private String title;
	private String category;
}
