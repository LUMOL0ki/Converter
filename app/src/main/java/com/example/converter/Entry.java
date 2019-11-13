package com.example.converter;

import android.media.Image;

public class Entry {
    public final String kod;
    private final String code;
    private final String country;
    private final String currency;
    private int amount;
    private double rate;

    // TODO 3. Rozsirit dalsi udaje ve tride, ktere se budou vest pro kazdou menu
    // TODO 3. To zahrnuje i upraveni konstruktoru
            
    Entry(String code, String country, String currency, int amount, double rate) {//this.country = country;
        this.kod = code;
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
