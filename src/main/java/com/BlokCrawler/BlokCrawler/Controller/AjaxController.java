package com.BlokCrawler.BlokCrawler.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlokCrawler.BlokCrawler.Model.DailyInformation;
import com.BlokCrawler.BlokCrawler.Service.InformationService;

@RestController
public class AjaxController {

    @Autowired
    InformationService informationService;

    @RequestMapping("/startCrawler")
    public void startCrawler() {
        informationService.writeInformationToBlok();
    }

    @RequestMapping("/test")
    public List<DailyInformation> test() {
        return informationService.getWeeklyInformationFromMock();
    }
}
