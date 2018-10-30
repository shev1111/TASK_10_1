package com.shev;

import com.shev.dao.AmazonItemXML;
import com.shev.service.AmazonServiceItemRetrieve;

public class MainItemRetrieve {
    public static void main(String[] args) {
        String url = "https://www.amazon.com/LG-gram-Thin-Light-Laptop/dp/B078WRSHV4/ref=sr_1_82_sspa";
        AmazonServiceItemRetrieve amazonService = new AmazonServiceItemRetrieve(url);
        System.out.println(amazonService.getLapTop());
        AmazonItemXML.addLapTopDataToXML(amazonService.getLapTop());
    }
}
