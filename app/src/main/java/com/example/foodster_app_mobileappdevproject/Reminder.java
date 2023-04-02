package com.example.foodster_app_mobileappdevproject;

public class Reminder {
    private String order;
    private String names;
    private String amounts;
    private int reminder;

    public Reminder(String order, String names, String amounts, int reminder){
        this.order = order;
        this.names = names;
        this.amounts = amounts;
        this.reminder = reminder;
    }

    public String getOrder(){
        return order;
    }
    public String getNames(){
        return names;
    }
    public String getAmounts(){
        return amounts;
    }
    public int getReminder(){
        return reminder;
    }
}
