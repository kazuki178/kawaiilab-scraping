package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.GroupSchedule;
import com.example.demo.service.CandyTurnService;
import com.example.demo.service.CutieStreetService;
import com.example.demo.service.FruitszipperService;
import com.example.demo.service.KawaiiLabService;

@Controller
public class IndexController {
	@Autowired
	KawaiiLabService kawaiiLabService;
	@Autowired
	FruitszipperService fruitszipperService;
	@Autowired
	CutieStreetService cutieStreetService;
	@Autowired
	CandyTurnService candyTurnService;

	@GetMapping("/index")
	public String index(Model model) {

		// List<String> kawailabScheduleList = kawaiiLabService.showScheduleInfo();
		// List<String> fruitzipperScheduleList =
		// fruitszipperService.showScheduleInfo();
		// model.addAttribute("kawailabScheduleList", kawailabScheduleList);
		// model.addAttribute("fruitzipperScheduleList", fruitzipperScheduleList);  
		return "index";
	}

	// ここで先月来月を判定して、date変数をそれに応じて変更して、
	// そこからyearとmonthを取り出して、modelに格納して変化させていく
	@GetMapping("/detailInfo")
	public String detailInfo(@RequestParam("id") int id,
			@RequestParam(name = "status", defaultValue = "") String status,
			@RequestParam(name = "date", required = false) LocalDate date,
			Model model) {

		List<GroupSchedule> ScheduleList = new ArrayList<>();

		// 1日開始で作成dされるように処理
		String title = null;
		String group = null;
		if (date == null) {
			date = LocalDate.now().withDayOfMonth(1);
		}

		if (status.equals("nextMonth")) {
			date = date.plusMonths(1);
		} else if (status.equals("prevMonth")) {
			date = date.minusMonths(1);
		}
		// 年（4桁）と月（2桁）を文字列で取得
		String year = String.format("%04d", date.getYear()); // 例: "2025"
		String month = String.format("%02d", date.getMonthValue()); // 例: "05"

		switch (id) {
			case 1: {
				ScheduleList = kawaiiLabService.showScheduleInfo(year, month);
				title = "KAWAII LAB.";
				group = "kawaiiLab";
				break;
			}
			case 2: {
				ScheduleList = fruitszipperService.showScheduleInfo();
				title = "FRUITS ZIPPER";
				group = "fruitsZipper";
				break;
			}
			case 3: {
				ScheduleList = cutieStreetService.showScheduleInfo();
				title = "CUTIE STREET";
				group = "cutieStreet";
				break;
			}
			case 4: {
				ScheduleList = candyTurnService.showScheduleInfo();
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
