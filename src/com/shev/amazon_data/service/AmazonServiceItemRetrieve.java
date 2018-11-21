package com.shev.amazon_data.service;

import com.shev.amazon_data.model.Item;
import com.shev.amazon_data.model.LapTop;
import com.shev.amazon_data.model.LapTopTechSpec;
import com.shev.amazon_data.model.SimpleItem;
import com.shev.amazon_data.utils.AmazonUtil;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;


public class AmazonServiceItemRetrieve {
    private static Logger logger = Logger.getLogger(AmazonServiceItemRetrieve.class.getName());
    private String url;
    private static Document document;
    private final static String US_PROXY_IP = "74.93.210.173";
    private final static int US_PROXY_IP_PORT = 53255;

    public AmazonServiceItemRetrieve(String url) {
        this.url = url;
        try {
            Connection.Response response = Jsoup.connect(this.url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:64.0) Gecko/20100101 Firefox/64.0")
                    .maxBodySize(0)
                    .timeout(6000000)
                    //.proxy(US_PROXY_IP,US_PROXY_IP_PORT)
                    .execute();
            int statusCode = response.statusCode();
            if (statusCode==200){
                document = response.parse();
                logger.info("response code 200 "+"url = "+this.url);
                logger.info("document object was assigned");
            }else {
                logger.error("response code: "+response.statusCode());
                logger.error("response status message: "+response.statusMessage());
            }
        } catch (IOException e ) {
            logger.error(e.getMessage() + " for url = " + this.url);
        } catch (IllegalArgumentException e){
            logger.error(e.getMessage());
        }
    }

    private String getASIN(){
        if(document!=null){
            Element table = document.select("table#productDetails_detailBullets_sections1").first();
            Elements rows = table.select("tr");
            Iterator<Element> rowIterator = rows.iterator();

            while (rowIterator.hasNext()){
                Element row = rowIterator.next();
                Elements colHeaders = row.select("th");
                Elements col = row.select("td");
                String headerName = colHeaders.text();
                switch (headerName){
                    case "ASIN": return col.text();
                }
            }
        }
        return null;
    }

    private Integer getPriceCents(){

        if(document!=null){
            try {
                String stringPrice = document.select("span#price_inside_buybox").text();
                if (stringPrice.equals("")){
                    stringPrice=document.select("span#priceblock_ourprice").text();
                }

                String stringPriceCents = stringPrice.replaceAll("[$,.]","");
                return Integer.valueOf(stringPriceCents);
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
                return 0;
            }
        }
        return null;

    }

    private String getAvailability(){
        if(document!=null){
            String availability = document.select("div#availability").text();
            return availability.replace(".", "");
        }
        return null;

    }

    private String getProductTitle(){
        if(document!=null){
           return document.select("span#productTitle").text();
        }
        return null;

    }

    private LapTopTechSpec lapTopTechSpec(){
        LapTopTechSpec lapTopTechSpec = new LapTopTechSpec();
        Element table = document.select("table#productDetails_techSpec_section_1").first();
        Elements rows = table.select("tr");
        Iterator<Element> rowIterator = rows.iterator();
        while (rowIterator.hasNext()){
            Element row = rowIterator.next();
            Elements colHeaders = row.select("th");
            Elements col = row.select("td");
            String headerName = colHeaders.text();
            switch (headerName){
                case "Screen Size":lapTopTechSpec.setScreenSize(col.text());break;
                case "Max Screen Resolution":lapTopTechSpec.setMaxScreenResolution(col.text());break;
                case "Processor":lapTopTechSpec.setProcessor(col.text());break;
                case "RAM": lapTopTechSpec.setRam(col.text());break;
                case "Hard Drive": lapTopTechSpec.setHardDrive(col.text());break;
                case "Graphics Coprocessor": lapTopTechSpec.setGraphicsCoprocessor(col.text());break;
                case "Card Description": lapTopTechSpec.setCardDescription(col.text());break;
                case "Wireless Type": lapTopTechSpec.setWirelessType(col.text());break;
                case "Number of USB 3.0 Ports": lapTopTechSpec.setNumberOfUSB3Ports(col.text());break;
            }
        }
        return lapTopTechSpec;
    }

    public String getTypeOfItem(){
        if(document!=null){
            Element wayFindingElement = document.select("div#wayfinding-breadcrumbs_feature_div").first();
            Element itemTypeElement = wayFindingElement.select("li").last();
            Element linkToTypeElement = itemTypeElement.select("a").first();
            String ItemType = linkToTypeElement.text();
            return ItemType;
        }
        return null;
    }

    public SimpleItem getSimpleItem(){
        if(document!=null && AmazonUtil.notNull(getASIN(),getProductTitle(),getAvailability(), getPriceCents()))
        {
            logger.info("get laptop data from url = "+this.url);
            return new SimpleItem(getASIN(),
                    getProductTitle(),
                    getPriceCents(),
                    getAvailability());
        }
        logger.error("Simple object was not created, check url "+this.url);
        return null;
    }

    public LapTop getLapTop(){
        if(document!=null && AmazonUtil.notNull(getASIN(),getProductTitle(),getAvailability(),lapTopTechSpec(), getPriceCents()))
        {
            logger.info("get laptop data from url = "+this.url);
            return new LapTop(getASIN(),
                    getProductTitle(),
                    getPriceCents(),
                    getAvailability(),
                    lapTopTechSpec());
        }
        logger.error("LapTop object was not created, check url "+this.url);
        return null;
    }

    public Item getItem(){
        String itemType = getTypeOfItem();
        if (itemType.contains("Laptops"))return getLapTop();
        return getSimpleItem();
    }


}
