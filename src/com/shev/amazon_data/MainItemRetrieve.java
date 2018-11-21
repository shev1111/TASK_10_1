package com.shev.amazon_data;

import com.shev.amazon_data.dao.AmazonItemXML;
import com.shev.amazon_data.model.LapTop;
import com.shev.amazon_data.service.AmazonServiceItemRetrieve;

public class MainItemRetrieve {
    public static void main(String[] args) {
        String url = "https://www.amazon.com/LG-gram-Thin-Light-Laptop/dp/B078WRSHV4/ref=sr_1_82_sspa";

        AmazonServiceItemRetrieve amazonService = new AmazonServiceItemRetrieve(url);
        AmazonItemXML.addLapTopDataToXML(amazonService.getLapTop());
    }
}
