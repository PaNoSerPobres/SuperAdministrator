package com.example.superadministrator.Class.Entities;

public class Expenses {
    private String Category;
    private String Icon;
    private float Amount;
    private boolean Recurrent;
    private int TimeLapse;

    Expenses(){};

    public Expenses(String category, String icon, float amount, boolean recurrent, int timeLapse){
        Category = category;
        Icon = icon;
        Amount = amount;
        Recurrent = recurrent;
        TimeLapse = timeLapse;
    }


    public String getCategory() {
        return Category;
    }

    public String getIcon() {
        return Icon;
    }

    public float getAmount() {
        return Amount;
    }

    public boolean isRecurrent() {
        return Recurrent;
    }

    public int getTimeLapse() {
        return TimeLapse;
    }
}
