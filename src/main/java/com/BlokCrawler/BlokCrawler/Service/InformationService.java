package com.BlokCrawler.BlokCrawler.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.BlokCrawler.BlokCrawler.Crawler.BlokCrawler;
import com.BlokCrawler.BlokCrawler.Model.Attendance;
import com.BlokCrawler.BlokCrawler.Model.DailyInformation;
import com.BlokCrawler.BlokCrawler.Model.Task;

@Service
public class InformationService {

    public List<DailyInformation> getWeeklyInformationFromCsv(String pathToCsv) {
        return null;
    }

    public List<DailyInformation> getWeeklyInformationFromMock() {
        List<DailyInformation> weeklyInformation = new ArrayList<>();

        for (int i = 0; i <= 4; i++) {
            DailyInformation DailyInformation = new DailyInformation();

            List<Task> taskList = new ArrayList<>();
            taskList.add(new Task(5, "Egon"));
            taskList.add(new Task(10, "Test"));

            DailyInformation.setDalyTasks(taskList);
            DailyInformation.setDay(LocalDate.now().plusDays(1).plusDays(i));
            DailyInformation.setAttendance(Attendance.PRESENT);

            weeklyInformation.add(DailyInformation);
        }

        return weeklyInformation;
    }

    public void writeInformationToBlok() {

        List<DailyInformation> weeklyInformation = getWeeklyInformationFromMock();

        if (weeklyInformation != null && weeklyInformation.size() > 0 && weeklyInformation.size() < 6) {

            new BlokCrawler().writeInformationToBlok(weeklyInformation);

        } else {
            System.out.println("weeklyInformation error");
        }

    }

}
