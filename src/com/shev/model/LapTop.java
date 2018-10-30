package com.shev.model;

public class LapTop extends Item {
    private LapTopTechSpec techSpec;

    public LapTop(String asin, String productTitle, int price, String availability, LapTopTechSpec techSpec) {
        super(asin, productTitle, price, availability);
        this.techSpec = techSpec;
    }

    public LapTop() {
    }

    public LapTopTechSpec getTechSpec() {
        return techSpec;
    }

    @Override
    public String toString() {
        return "ASIN: "+this.getAsin()+"\n"+
                "Product Title: "+this.getProductTitle()+"\n"+
                "Price: "+this.getPriceCents()+"\n"+
                "Availability: "+this.getAvailability()+"\n"+
                techSpec;
    }
}
