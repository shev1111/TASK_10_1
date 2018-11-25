package com.shev.amazon_data.model;

public class SimpleItem extends Item {
    public SimpleItem(String asin, String productTitle, int priceCents, String availability, String url) {
        super(asin, productTitle, priceCents, availability, url);
    }

    public SimpleItem() {
    }
}
