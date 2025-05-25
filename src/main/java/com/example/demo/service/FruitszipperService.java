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
public class FruitszipperService {
	public List<GroupSchedule> showScheduleInfo() {
		//jsoupで書き換え前のデータを取得
		List<GroupSchedule> scheduleList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect("https://fruitszipper.asobisystem.com/live_information/schedule/list/")
					.get();
			Elements elements = doc.select(".sys-schedule");

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
