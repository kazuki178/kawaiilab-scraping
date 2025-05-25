package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.TicketInfo;

@Controller
public class PiaController {

	//jsoup
	@GetMapping("/pia/info/{group}")
	public String getPiaInfo(@PathVariable(name = "group") String group, Model model) {
		List<TicketInfo> acceptinsList = new ArrayList<>();
		List<TicketInfo> beforeResultsList = new ArrayList<>();
		try {
			// 実際のブラウザを起動してjs書き換え後のデータを取得
			WebDriver driver = new ChromeDriver();
			if (group.equals("kawaiiLab")) {
				driver.get("https://t.pia.jp/pia/event/event.do?eventBundleCd=b2558257");
			} else if (group.equals("fruitsZipper")) {
				driver.get("https://t.pia.jp/pia/artist/artists.do?artistsCd=M4140001");
			} else if (group.equals("cutieStreet")) {
				driver.get("https://t.pia.jp/pia/artist/artists.do?artistsCd=O7260002");
			} else if (group.equals("candyTurn")) {
				driver.get("https://t.pia.jp/pia/artist/artists.do?artistsCd=N1260021");
			}

			List<WebElement> tableDataElements = driver.findElements(By.cssSelector(".table_data"));

			for (WebElement tableDataElement : tableDataElements) {
				WebElement statusElement = null;
				try {
					statusElement = tableDataElement.findElement(By.cssSelector(".is_status"));
				} catch (NoSuchElementException e) {
					continue; // statusがなければスキップ
				}

				if (statusElement.getText().contains("抽選受付中")) {

					String day = tableDataElement.findElement(By.cssSelector(".is_date")).getText();
					String place = tableDataElement.findElement(By.cssSelector(".is_place")).getText();
					String title = tableDataElement.findElement(By.cssSelector(".is_title")).getText();
					acceptinsList.add(new TicketInfo(day, title, place));

				} else if (statusElement.getText().contains("抽選結果発表前")) {

					String day = tableDataElement.findElement(By.cssSelector(".is_date")).getText();
					String place = tableDataElement.findElement(By.cssSelector(".is_place")).getText();
					String title = tableDataElement.findElement(By.cssSelector(".is_title")).getText();
					beforeResultsList.add(new TicketInfo(day, title, place));

				} else {
					continue;
				}

			}
			driver.quit();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			model.addAttribute("error", "リクエストされた要素が見つかりませんでした");
		}
		boolean acceptinsListFlag = true;
		boolean beforeResultsListFlag = true;

		// piaで募集中のチケットが見つからない場合の処理
		if (acceptinsList.size() == 0) {
			acceptinsListFlag = false;
			model.addAttribute("acceptinsListFlag",acceptinsListFlag);
		}
		if (beforeResultsList.size() == 0) {
			beforeResultsListFlag = false;
			model.addAttribute("beforeResultsListFlag",beforeResultsListFlag);
		}
		

		model.addAttribute("acceptinsList", acceptinsList);
		model.addAttribute("beforeResultsList", beforeResultsList);
		
		return "piaTicket";
	}
	
	
}