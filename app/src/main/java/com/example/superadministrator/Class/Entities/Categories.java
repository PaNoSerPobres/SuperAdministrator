package com.example.superadministrator.Class.Entities;

public class Categories {
private String Type;
private String Image;

public Categories() {}

public Categories(String type, String image)
{
    Type = type;
    Image = image;
}

public String getType() {
    return Type;
}

public String getImage() {
    return Image;
}
}
