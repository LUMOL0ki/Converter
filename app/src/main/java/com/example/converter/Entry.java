package com.example.converter;

public class Entry {
    public final String kod;
    private String country;
    private Double current;
 
    // TODO 3. Rozsirit dalsi udaje ve tride, ktere se budou vest pro kazdou menu
    // TODO 3. To zahrnuje i upraveni konstruktoru
            
    Entry(String kod, String country, Double current) {
        this.country = country;
        this.kod = kod;
        this.current = current;
    }
}
