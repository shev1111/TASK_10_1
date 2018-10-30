package com.shev.service;

import com.shev.model.LapTop;
import com.shev.model.LapTopTechSpec;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;


public class AmazonService {
    private String url;
    private static Document document;
    private final static String US_PROXY_IP = "205.145.146.174";
    private final static int US_PROXY_IP_PORT = 41004;

    public AmazonService(String url) {
        this.url = url;
        //throws java.net.ConnectException: Connection timed out: connect and java.net.SocketException: Connection reset
        try {
            document = Jsoup.connect(this.url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:64.0) Gecko/20100101 Firefox/64.0")
                    .maxBodySize(0)
                    .timeout(600000)
                    .proxy(US_PROXY_IP,US_PROXY_IP_PORT)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getASIN(){
        Element table = document.select("table#productDetails_detailBullets_sections1").first();
        Elements rows = table.select("tr");
        Iterator<Element> rowIterator = rows.iterator();
        String asin = null;
        while (rowIterator.hasNext()){
            Element row = rowIterator.next();
            Elements colHeaders = row.select("th");
            Elements col = row.select("td");
            String headerName = colHeaders.text();
            switch (headerName){
                case "ASIN":asin = col.text();return asin;
            }
        }
        return asin;
    }

    private int getPriceCents(){
        String stringPrice = document.select("span#price_inside_buybox").text();
        //$1,147.00
        if (stringPrice.equals("")){
            stringPrice=document.select("span#priceblock_ourprice").text();
        }

        String stringPriceCents = stringPrice.replaceAll("[$,.]","");
        /*if  java.lang.NumberFormatException: For input string: "" write it to log with url*/
        return Integer.valueOf(stringPriceCents);

    }

    private String getAvailability(){
        String availability = document.select("div#availability").text();
        return availability.replace(".", "");
    }

    private String getProductTitle(){
        return document.select("span#productTitle").text();
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
