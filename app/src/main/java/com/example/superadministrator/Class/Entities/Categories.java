package com.example.superadministrator.Class.Entities;

public class Categories {
private String Type;
private String Image;
private boolean IsExpense;

public Categories() {}

public Categories(String type, String image, boolean isExpense)
{
    Type = type;
    Image = image;
    IsExpense = isExpense;
}
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public boolean isExpense() {
        return IsExpense;
    }

    public void setExpense(boolean expense) {
        IsExpense = expense;
    }
}
