package com.example.converter;

import android.media.Image;

public class Entry {
    private final String code;
    private final String country;
    private final String currency;
    private int amount;
    private double rate;

    Entry(String code, String country, String currency, int amount, double rate) {//this.country = country;
        this.code = code;
        this.country = country;
        this.currency = currency;
        this.amount = amount;
        this.rate = rate;
    }

    public String getCode(){
        return code;
    }

    public String getCountry(){
        return country;
    }

    public String getCurrency(){
        return currency;
    }

    public int getAmount(){
        return amount;
    }

    public double getRate(){
        return rate;
    }
}
