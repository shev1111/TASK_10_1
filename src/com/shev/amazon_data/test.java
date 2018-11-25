package com.shev.amazon_data;

import com.shev.amazon_data.dao.ConnectionDB;
import com.shev.amazon_data.service.BuyingService;
import com.shev.amazon_data.service.RegisterService;
import com.shev.amazon_data.utils.JdbcPropManager;
import org.apache.commons.validator.routines.EmailValidator;
import org.openqa.selenium.WebDriver;

public class test {
    public static void main(String[] args) {
        //WebDriver driver = BuyingService.addItemToCartByLink("https://www.amazon.com/VivoBook-Nanoedge-i5-8250U-Processor-Fingerprint/dp/B0762S8PYM/ref=sr_1_6?s=pc&ie=UTF8&qid=1542483879&sr=1-6&keywords=laptop", "relenocewe@daabox.com", "12345678");
       // WebDriver driver = BuyingService.addItemToCartByASIN("B07GC1HJ89", "relenocewe@daabox.com", "12345678");
       /* WebDriver driver = BuyingService.addItemToCartByLink("https://www.amazon.com/Storage-Childrens-Organizer-Comfortable-Seating/dp/B0788FKHDX/ref=sr_1_1_sspa?s=movies-tv&ie=UTF8&qid=1542527941&sr=1-1-spons&keywords=chair&th=1",
                "relenocewe@daabox.com", "12345678");*/
        ConnectionDB.getConnection();



    }
}
