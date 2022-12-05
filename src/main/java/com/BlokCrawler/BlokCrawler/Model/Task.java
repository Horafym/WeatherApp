package com.BlokCrawler.BlokCrawler.Model;

public class Task {

    Integer neededTime;
    String description;

    public Task(Integer neededTime, String description) {
        this.neededTime = neededTime;
        this.description = description;
    }

    public Integer getNeededTime() {
        return this.neededTime;
    }

    public void setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
