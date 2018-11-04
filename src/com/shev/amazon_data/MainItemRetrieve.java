package com.shev.amazon_data;

import com.shev.amazon_data.dao.AmazonItemXML;
import com.shev.amazon_data.model.LapTop;
import com.shev.amazon_data.service.AmazonServiceItemRetrieve;

public class MainItemRetrieve {
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String SEP = System.getProperty("file.separator");
    private static final String XML_FILE = USER_DIR+SEP+"src"+SEP+"com"+SEP+"shev"+SEP+"amazon_data"+SEP+"data"+SEP+"amazon_item_data.xml";

    public static void main(String[] args) {
        /*String url = "https://www.amazon.com/LG-gram-Thin-Light-Laptop/dp/B078WRSHV4/ref=sr_1_82_sspa";
        AmazonServiceItemRetrieve amazonService = new AmazonServiceItemRetrieve(url);
        AmazonItemXML.addLapTopDataToXML(amazonService.getLapTop());*/
        LapTop lapTop1 = AmazonItemXML.findLapTopASIN("B078WRSHV4",XML_FILE);
        LapTop lapTop2 = AmazonItemXML.findLapTopASIN("B078WRSHV4",XML_FILE);
//        System.out.println(AmazonItemXML.findLapTopASIN("B078WRSHV4",XML_FILE));
        System.out.println(lapTop1.equals(lapTop2));
    }
}
