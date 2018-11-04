package com.shev.amazon_data.model;

public abstract class Item {
    private String asin;
    private String productTitle;
    private int priceCents;
    private String availability;

    public Item(String asin, String productTitle, int priceCents, String availability) {
        this.asin = asin;
        this.productTitle = productTitle;
        this.priceCents = priceCents;
        this.availability = availability;
    }

    public Item() {
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public void setPriceCents(int priceCents) {
        this.priceCents = priceCents;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

}
