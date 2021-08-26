package com.ranzan.moneymanagerclone;

public class Data {
    private String type;
    private String date;
    private String time;
    private String account;
    private String category;
    private int amount;
    private String note;
    private String description;

    public Data(String type, String date, String time, String account, String category, int amount, String note, String description) {
        this.type = type;
        this.date = date;
        this.time = time;
        this.account = account;
        this.category = category;
        this.amount = amount;
        this.note = note;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAccount() {
        return account;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public String getDescription() {
        return description;
    }
}
