package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.example.demo.model.GroupSchedule;

@Service
public class KawaiiLabService {
	public List<GroupSchedule> showScheduleInfo(String year, String month) {
		//jsoupで書き換え前のデータを取得
		List<GroupSchedule> scheduleList = new ArrayList<>();
		try {
			String url = "https://kawaiilab.asobisystem.com/live_information/schedule/list/" +
					"?year=" + year + "&month=" + month;
			Document doc = Jsoup.connect(url)
					.get();
			Elements elements = doc.select(".sys-schedule a");

			for (Element element : elements) {
				scheduleList.add(new GroupSchedule(element.select(".block--date__month").text(),
						element.select(".block--date__date").text(),
						element.select(".tit").text(),
						element.select(".category").text()));

			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}
		return scheduleList;
	}
}
