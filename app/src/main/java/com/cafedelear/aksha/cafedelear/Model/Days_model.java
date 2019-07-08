package com.cafedelear.aksha.cafedelear.Model;

import java.sql.Time;

public class Days_model {

    private String dayId;
    private String dayName;
    private String  startTime;
    private String endTime;
    private boolean isCheck;

    public Days_model() {
    }

    public Days_model(String dayId, String dayName, String startTime, String endTime,boolean isCheck) {
        this.dayId = dayId;
        this.dayName = dayName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isCheck = isCheck;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }


}
