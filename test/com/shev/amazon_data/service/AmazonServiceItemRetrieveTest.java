package com.shev.amazon_data.service;

import com.shev.amazon_data.model.LapTop;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AmazonServiceItemRetrieveTest {
   private static LapTop lapTop = null;
    @BeforeClass
    public static void before(){
        String url = "https://www.amazon.com/LG-gram-Thin-Light-Laptop/dp/B078WRSHV4/ref=sr_1_82_sspa";
        AmazonServiceItemRetrieve amazonService = new AmazonServiceItemRetrieve(url);
        lapTop = amazonService.getLapTop();
    }

    @Test
    public void getLapTopTest() throws Exception {
        String expTechSpec = "ScreenSize: 15.6 inches\n" +
                "MaxScreenResolution: 1920 x 1080 pixels\n" +
                "Processor: 1.6 GHz\n" +
                "RAM: 8 GB\n" +
                "HardDrive: 256 GB Flash Memory Solid State\n" +
                "GraphicsCoprocessor: Intel UHD Graphics 620\n" +
                "CardDescription: Dedicated\n" +
                "WirelessType: 802.11.ac\n" +
                "NumberOfUSB3Ports: 3";
        assertTrue(lapTop!=null);
        assertEquals("B078WRSHV4",lapTop.getAsin());
        assertEquals("LG gram Thin and Light Laptop - 15.6\" " +
                "Full HD IPS Display, Intel Core i5 (8th Gen), 8GB RAM, " +
                "256GB SSD, Back-lit Keyboard - Dark Silver – 15Z980-U.AAS5U1",lapTop.getProductTitle());
        assertEquals(114700,lapTop.getPriceCents());
        assertEquals("Only 19 left in stock (more on the way)",lapTop.getAvailability());
        assertEquals(expTechSpec, lapTop.getTechSpec().toString());

    }


}
