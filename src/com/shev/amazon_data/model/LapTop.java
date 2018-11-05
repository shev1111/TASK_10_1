package com.shev.amazon_data.model;

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

    public void setTechSpec(LapTopTechSpec techSpec) {
        this.techSpec = techSpec;
    }

    @Override
    public String toString() {
        return "\nASIN: "+this.getAsin()+"\n"+
                "Product Title: "+this.getProductTitle()+"\n"+
                "Price: "+this.getPriceCents()+"\n"+
                "Availability: "+this.getAvailability()+"\n"+
                techSpec;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LapTop lapTop = (LapTop) obj;
        return this.getAsin().equals(lapTop.getAsin());
    }

    @Override
    public int hashCode() {
        return this.getAsin().hashCode();
    }
}
