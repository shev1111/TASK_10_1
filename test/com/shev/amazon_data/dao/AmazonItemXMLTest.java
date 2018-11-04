package com.shev.amazon_data.dao;

import com.shev.amazon_data.model.LapTop;
import com.shev.amazon_data.service.AmazonServiceItemRetrieve;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AmazonItemXMLTest {
    private static LapTop lapTop = null;
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String SEP = System.getProperty("file.separator");
    private static final String XML_FILE = USER_DIR+SEP+"src"+SEP+"com"+SEP+"shev"+SEP+"amazon_data"+SEP+"data"+SEP+"amazon_item_data.xml";

    @BeforeClass
    public static void before(){
        String url = "https://www.amazon.com/LG-gram-Thin-Light-Laptop/dp/B078WRSHV4/ref=sr_1_82_sspa";
        AmazonServiceItemRetrieve amazonService = new AmazonServiceItemRetrieve(url);
        lapTop = amazonService.getLapTop();
    }
    @Test
    public void addLapTopDataToXML() throws Exception {
        AmazonItemXML.addLapTopDataToXML(lapTop);
        LapTop testLapTop = AmazonItemXML.findLapTopASIN(lapTop.getAsin(), XML_FILE);
        assertEquals(lapTop,testLapTop);
    }

    @Test
    public void findLapTopASIN(){
        String expASIN = "B078WRSHV4";
        LapTop testLaptop = AmazonItemXML.findLapTopASIN(lapTop.getAsin(), XML_FILE);
        assertNotNull(testLaptop);
        assertEquals(testLaptop.getAsin(),expASIN);
    }

}
