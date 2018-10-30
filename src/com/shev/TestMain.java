package com.shev;

import com.shev.dao.AmazonItemXML;
import com.shev.service.AmazonService;

public class TestMain {
    public static void main(String[] args) {
        String url = "https://www.amazon.com/LG-gram-Thin-Light-Laptop/dp/B078WRSHV4/ref=sr_1_82_sspa";
        String url2 = "https://www.amazon.com/dp/B078WRN1S8/ref=sspa_dk_detail_1?psc=1&pd_rd_i=B078WRN1S8&pf_rd_m=ATVPDKIKX0DER&pf_rd_p=f52e26da-1287-4616-824b-efc564ff75a4&pf_rd_r=BNA5793M37RPZNBA80BB&pd_rd_wg=8B1j6&pf_rd_s=desktop-dp-sims&pf_rd_t=40701&pd_rd_w=u9cuF&pf_rd_i=desktop-dp-sims&pd_rd_r=875e5f1d-d92b-11e8-8a6a-edd5ab8179b1";
        String url1 = "https://www.amazon.com/dp/B079TGL2BZ/ref=psdc_13896615011_t1_B078WRSHV4";

        AmazonService amazonService = new AmazonService(url);
        System.out.println(amazonService.getLapTop());
        AmazonItemXML.addLapTopDataToXML(amazonService.getLapTop());
    }
}
