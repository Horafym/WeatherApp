package com.BlokCrawler.BlokCrawler.Model;

import java.time.LocalDate;
import java.util.List;

public class DailyInformation {

    LocalDate day;
    Attendance attendance;
    List<Task> dalyTasks;

    public LocalDate getDay() {
        return this.day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Attendance getAttendance() {
        return this.attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public List<Task> getDalyTasks() {
        return this.dalyTasks;
    }

    public void setDalyTasks(List<Task> dalyTasks) {
        this.dalyTasks = dalyTasks;
    }

}
