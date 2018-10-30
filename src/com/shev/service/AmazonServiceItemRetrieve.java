package com.shev.service;

import com.shev.model.LapTop;
import com.shev.model.LapTopTechSpec;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Iterator;


public class AmazonServiceItemRetrieve {
    static Logger logger = Logger.getLogger(AmazonServiceItemRetrieve.class.getName());
    private String url;
    private static Document document;
    private final static String US_PROXY_IP = "205.145.146.174";
    private final static int US_PROXY_IP_PORT = 41004;

    public AmazonServiceItemRetrieve(String url) {
        this.url = url;
        //throws java.net.ConnectException: Connection timed out: connect and java.net.SocketException: Connection reset
        try {
            Connection.Response response = Jsoup.connect(this.url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:64.0) Gecko/20100101 Firefox/64.0")
                    .maxBodySize(0)
                    .timeout(6000000)
                    .proxy(US_PROXY_IP,US_PROXY_IP_PORT)
                    .execute();
            int statusCode = response.statusCode();
            if (statusCode==200){
                document = response.parse();
                logger.info("response code 200 "+"url = "+this.url);
                logger.info("document object assigned");
            }else {
                logger.error("response code: "+response.statusCode());
                logger.error("response status message: "+response.statusMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                //LOG /*if  java.lang.NumberFormatException: For input string: "" write it to log with url*/
                e.printStackTrace();
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
        /*check if laptop*/
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

    public LapTop getLapTop(){
        return new LapTop(getASIN(),
                getProductTitle(),
                getPriceCents(),
                getAvailability(),
                lapTopTechSpec());
    }

}
