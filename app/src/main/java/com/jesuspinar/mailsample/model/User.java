package com.jesuspinar.mailsample.model;

public class User {
    private static int AUTO_ID = 0;
    private final int id;

    private final String name;
    private final String surnames;
    private final String birth;

    public User (String name, String surnames, String birth) {
        id = ++AUTO_ID;
        this.name = name;
        this.surnames = surnames;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getBirth() {
        return birth;
    }
}
