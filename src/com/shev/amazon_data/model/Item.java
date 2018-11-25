package com.shev.amazon_data.model;

public abstract class Item {
    private String asin;
    private String productTitle;
    private int priceCents;
    private String availability;
    private String url;

    public Item(String asin, String productTitle, int priceCents, String availability, String url) {
        this.asin = asin;
        this.productTitle = productTitle;
        this.priceCents = priceCents;
        this.availability = availability;
        this.url = url;
    }

    public Item() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public String toString() {
        return "Amazon Item:" +
                "\nasin='" + asin + '\'' +
                ",\nproductTitle ='" + productTitle + '\'' +
                ",\npriceCents =" + priceCents +
                ",\navailability ='" + availability;
    }
}
