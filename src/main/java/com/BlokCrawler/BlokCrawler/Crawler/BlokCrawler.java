package com.BlokCrawler.BlokCrawler.Crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.BlokCrawler.BlokCrawler.Definition.ExternalCrawler;
import com.BlokCrawler.BlokCrawler.Model.DailyInformation;
import com.BlokCrawler.BlokCrawler.Util.CrawlerUtils;

public class BlokCrawler implements ExternalCrawler {

    WebDriver driver;

    private void setUpDriver() {
        String HOST_URL = "http://localhost:4444/wd/hub";

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        try {
            driver = new RemoteWebDriver(new URL(HOST_URL), options);

            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);
            driver.manage().window().maximize();

        } catch (MalformedURLException e) {
            System.out.println("Error at HOST_URL");
            e.printStackTrace();
        }

    }

    @Override
    public void writeInformationToBlok(List<DailyInformation> weeklyInformation) {

        setUpDriver();

        if (driver != null) {

            try {

                doLogin();

                CrawlerUtils.waitForElement(driver, "//*[@id='services']");
                driver.findElement(By.xpath("//a[@title='Berichtsheft']")).click();

                goToRightWeek(weeklyInformation.get(0).getDay());

            } catch (Exception e) {
                System.out.println("error while crawling");
                e.printStackTrace();
            }

            driver.quit();
        }
    }

    private void goToRightWeek(LocalDate day) throws InterruptedException, ParseException {

        Boolean isOnRightWeek = false;

        while (!isOnRightWeek) {

            // ! __ FIND WAY TO BETTER WAIT __ !
            LocalDate currentDay;

            currentDay = getCurrentDay();

            if (currentDay.equals(day)) {

                isOnRightWeek = true;

            } else if (currentDay.isAfter(day)) {

                CrawlerUtils.waitForElement(driver, "//*[@class='timebackward']");
                driver.findElement(By.xpath("//*[@class='timebackward']")).click();

            } else if (currentDay.isBefore(day)) {

                CrawlerUtils.waitForElement(driver, "//*[@class='timeforward']");
                driver.findElement(By.xpath("//*[@class='timeforward']")).click();
            }
        }

    }

    private LocalDate getCurrentDay() throws InterruptedException, ParseException {

        Thread.sleep(5000);

        CrawlerUtils.waitForElement(driver, "//*[@class='time']");
        return CrawlerUtils.convertDate(driver.findElement(By.xpath("//*[@class='time']")).getText().replace(".", "-"));
    }

    private void doLogin() {
        driver.get("https://www.online-ausbildungsnachweis.de/blok/");

        CrawlerUtils.waitForElement(driver, "//span[text()='Benutzername']");

        WebElement nameInput = driver.findElement(By.xpath("//span[text()='Benutzername']/../../input"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@type='password']"));

        nameInput.sendKeys("USER");
        passwordInput.sendKeys("PW");

        driver.findElement(By.xpath("//a[@name='submitLogin']")).click();
    }
}
