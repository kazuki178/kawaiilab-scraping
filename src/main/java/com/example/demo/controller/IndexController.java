package com.example.demo.controller;

import com.example.demo.model.GroupSchedule;
import com.example.demo.service.GroupScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController {
	@Autowired
	GroupScheduleService groupScheduleService;

	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}

	// ここで先月来月を判定して、date変数をそれに応じて変更して、
	// そこからyearとmonthを取り出して、modelに格納して変化させていく
	@GetMapping("/detailInfo")
	public String detailInfo(@RequestParam("groupId") int id,
			@RequestParam(name = "monthStatus", defaultValue = "") String monthStatus,
			@RequestParam(name = "date", required = false) LocalDate date,
			Model model) {

		List<GroupSchedule> ScheduleList = new ArrayList<>();

		// 1日から開始で作成されるように処理
		String title = null;
		String group = null;
		if (date == null) {
			date = LocalDate.now().withDayOfMonth(1);
		}

		if (monthStatus.equals("nextMonth")) {
			date = date.plusMonths(1);
		} else if (monthStatus.equals("prevMonth")) {
			date = date.minusMonths(1);
		}
		// 年（4桁）と月（2桁）を文字列で取得
		String year = String.format("%04d", date.getYear()); // 例: "2025"
		String month = String.format("%02d", date.getMonthValue()); // 例: "05"

		switch (id) {
			case 1: {
				String url = "https://kawaiilab.asobisystem.com/live_information/schedule/list/";
				ScheduleList = groupScheduleService.showScheduleInfo(url, year, month);
				title = "KAWAII LAB.";
				group = "kawaiiLab";
				break;
			}
			case 2: {
				String url = "https://fruitszipper.asobisystem.com/live_information/schedule/list/";
				ScheduleList = groupScheduleService.showScheduleInfo(url, year, month);

				title = "FRUITS ZIPPER";
				group = "fruitsZipper";
				break;
			}
			case 3: {
				String url = "https://cutiestreet.asobisystem.com/live_information/schedule/list/";
				ScheduleList = groupScheduleService.showScheduleInfo(url, year, month);
				title = "CUTIE STREET";
				group = "cutieStreet";
				break;
			}
			case 4: {
				String url = "https://candytune.asobisystem.com/live_information/schedule/list/";
				ScheduleList = groupScheduleService.showScheduleInfo(url, year, month);
				title = "CANDY TUNE";
				group = "candyTune";
				break;
			}
		}

		model.addAttribute("ScheduleList", ScheduleList);
		model.addAttribute("title", title);
		model.addAttribute("group", group);
		model.addAttribute("id", id);
		model.addAttribute("date", date);
		return "detailInfo";
	}

}
