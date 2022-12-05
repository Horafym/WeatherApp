package com.BlokCrawler.BlokCrawler.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrawlerUtils {

    private static int maxWaitSecounds = 30;

    public static void waitForElement(WebDriver webDriver, String xPath) throws StaleElementReferenceException {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(maxWaitSecounds));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xPath)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));

    }

    public static LocalDate convertDate(String day) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(day);

        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return localDate;
    }
}
