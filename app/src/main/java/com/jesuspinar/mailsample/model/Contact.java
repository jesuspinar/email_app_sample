package com.jesuspinar.mailsample.model;

public class Contact {
    private static int AUTO_ID = 0;
    private final int id;

    private final String name;
    private final String firstSurname;
    private final String secondSurname;
    private final String birth;
    private final String email;
    private final String photo;
    private final String phone1;
    private final String phone2;
    private final String address;

    public Contact(String name, String firstSurname, String secondSurname,String photo, String birth, String email,  String phone1, String phone2,String address) {
        id = AUTO_ID++;
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.birth = birth;
        this.photo = photo;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public String getBirth() {
        return birth;
    }

    public String getEmail() {
        return email;
    }
}
