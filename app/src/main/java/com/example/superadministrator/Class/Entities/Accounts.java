package com.example.superadministrator.Class.Entities;

public class Accounts {
    private String Name;
    private String Type;
    private float Balance;

    public Accounts(){};

    public Accounts(String name, String type, float balance){
        Name=name;
        Type=type;
        Balance=balance;
    };

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    public float getBalance() {
        return Balance;
    }
}
