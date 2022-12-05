package com.BlokCrawler.BlokCrawler.Definition;

import java.util.List;

import com.BlokCrawler.BlokCrawler.Model.DailyInformation;

public interface ExternalCrawler {

    void writeInformationToBlok(List<DailyInformation> weeklyInformation);

}
