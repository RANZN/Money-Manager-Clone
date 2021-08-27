package com.ranzan.moneymanagerclone.Login;

public class UserData {
    private String name, email;

    UserData() {

    }

    public UserData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

