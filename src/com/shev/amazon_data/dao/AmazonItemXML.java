package com.shev.amazon_data.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import com.shev.amazon_data.model.LapTop;
import com.shev.amazon_data.model.LapTopTechSpec;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class AmazonItemXML {
        private static Logger logger = Logger.getLogger(AmazonItemXML.class.getName());
        private static final String USER_DIR = System.getProperty("user.dir");
        private static final String SEP = System.getProperty("file.separator");
        private static final String XML_FILE = USER_DIR+SEP+"src"+SEP+"com"+SEP+"shev"+SEP+"amazon_data"+SEP+"data"+SEP+"amazon_item_data.xml";

        public static void addLapTopDataToXML(LapTop lapTop){
            boolean isExists = lapTop.getAsin().equals(findLapTopByASIN(lapTop.getAsin(), XML_FILE).getAsin());
            if (lapTop!=null&&isExists){
                logger.info("Write lapTop data to xml file "+ lapTop.toString());
                try {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    // root elements
                    Document doc;
                    Element rootElement;

                    if (new File(XML_FILE).exists()) {
                        doc = docBuilder.parse(XML_FILE);
                        rootElement = doc.getDocumentElement();

                    } else {
                        doc = docBuilder.newDocument();
                        rootElement = doc.createElement("amazon_data");
                        doc.appendChild(rootElement);
                    }


                    // item element
                    Element item = doc.createElement("Item");
                    rootElement.appendChild(item);

                    // set asin attribute to item element
                    Attr asinAttr = doc.createAttribute("asin");
                    if(lapTop.getAsin()!=null){
                        asinAttr.setValue(lapTop.getAsin());
                        item.setAttributeNode(asinAttr);
                    }

                    // set type element
                    Element type = doc.createElement("type");
                    type.appendChild(doc.createTextNode("laptop"));
                    item.appendChild(type);

                    //set itemTitle
                    Element productTitle = doc.createElement("product_title");
                    if(lapTop.getProductTitle()!=null){
                        productTitle.appendChild(doc.createTextNode(lapTop.getProductTitle()));
                        item.appendChild(productTitle);
                    }

                    //set itemPrice
                    Element price = doc.createElement("cent_price");
                    if(lapTop.getPriceCents()!=0){
                        price.appendChild(doc.createTextNode(String.valueOf(lapTop.getPriceCents())));
                        item.appendChild(price);
                    }

                    //set availability
                    Element availability = doc.createElement("availability");
                    if(lapTop.getAvailability()!=null){
                        availability.appendChild(doc.createTextNode(lapTop.getAvailability()));
                        item.appendChild(availability);
                    }


                    //set screenSize
                    Element screenSize = doc.createElement("screen_size");
                    if(lapTop.getTechSpec().getScreenSize()!=null){
                        screenSize.appendChild(doc.createTextNode(lapTop.getTechSpec().getScreenSize()));
                        item.appendChild(screenSize);
                    }


                    //set maxScreenResolution
                    Element maxScreenResolution = doc.createElement("max_screen_resolution");
                    if (lapTop.getTechSpec().getMaxScreenResolution()!=null){
                        maxScreenResolution.appendChild(doc.createTextNode(lapTop.getTechSpec().getMaxScreenResolution()));
                        item.appendChild(maxScreenResolution);
                    }


                    //set processor
                    Element processor = doc.createElement("processor");
                    if(lapTop.getTechSpec().getProcessor()!=null){
                        processor.appendChild(doc.createTextNode(lapTop.getTechSpec().getProcessor()));
                        item.appendChild(processor);
                    }

                    //set ram
                    Element ram = doc.createElement("ram");
                    if(lapTop.getTechSpec().getRam()!=null){
                        ram.appendChild(doc.createTextNode(lapTop.getTechSpec().getRam()));
                        item.appendChild(ram);
                    }


                    //set hardDrive
                    Element hardDrive = doc.createElement("hard_drive");
                    if(lapTop.getTechSpec().getHardDrive()!=null){
                        hardDrive.appendChild(doc.createTextNode(lapTop.getTechSpec().getHardDrive()));
                        item.appendChild(hardDrive);
                    }


                    //set graphicsCoprocessor
                    Element graphicsCoprocessor = doc.createElement("graphics_coprocessor");
                    if(lapTop.getTechSpec().getGraphicsCoprocessor()!=null){
                        graphicsCoprocessor.appendChild(doc.createTextNode(lapTop.getTechSpec().getGraphicsCoprocessor()));
                        item.appendChild(graphicsCoprocessor);
                    }


                    //set graphicsCoprocessor
                    Element cardDescription = doc.createElement("card_description");
                    if(lapTop.getTechSpec().getCardDescription()!=null){
                        cardDescription.appendChild(doc.createTextNode(lapTop.getTechSpec().getCardDescription()));
                        item.appendChild(cardDescription);
                    }


                    //set wirelessType
                    Element wirelessType = doc.createElement("wireless_type");
                    if(lapTop.getTechSpec().getWirelessType()!=null){
                        wirelessType.appendChild(doc.createTextNode(lapTop.getTechSpec().getWirelessType()));
                        item.appendChild(wirelessType);
                    }

                    //set numberOfUSB3Ports
                    Element numberOfUSB3Ports = doc.createElement("number_of_usb3_ports");
                    if(lapTop.getTechSpec().getNumberOfUSB3Ports()!=null){
                        numberOfUSB3Ports.appendChild(doc.createTextNode(lapTop.getTechSpec().getNumberOfUSB3Ports()));
                        item.appendChild(numberOfUSB3Ports);
                    }

                    // write the content into xml file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(XML_FILE);
                    transformer.transform(source, result);


                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                    logger.error("XML file error: "+e.getMessage());
                } catch (TransformerConfigurationException e) {
                    logger.error("XML file error: "+e.getMessage());
                } catch (TransformerException e) {
                    logger.error("XML file error: "+e.getMessage());
                } catch (SAXException e) {
                    logger.error("XML file error: "+e.getMessage());
                } catch (IOException e) {
                    logger.error("XML file error: "+e.getMessage());
                }
            }else {
                if(isExists){
                    logger.info("laptop with asin "+lapTop.getAsin()+ " already exists");
                }
                logger.error("laptop data were not added to xml file");
            }



        }

        public static LapTop findLapTopByASIN(String asin, String xmlFilePath){
            LapTop lapTop = null;
            LapTopTechSpec lapTopTechSpec;
            File fileXML = new File(xmlFilePath);
            try {
                FileInputStream fis = new FileInputStream(fileXML);
                org.jsoup.nodes.Document doc = Jsoup.parse(fis, null, "",Parser.xmlParser());
                for (org.jsoup.nodes.Element amazonItem:doc.getElementsByTag("Item")) {
                    if( asin!=null && amazonItem.attr("asin").equals(asin)){
                        lapTop = new LapTop();
                        lapTopTechSpec = new LapTopTechSpec();
                        lapTop.setAsin(amazonItem.attr("asin"));
                        lapTop.setProductTitle(amazonItem.getElementsByTag("product_title").text());
                        lapTop.setPriceCents(Integer.valueOf(amazonItem.getElementsByTag("cent_price").text()));
                        lapTop.setAvailability(amazonItem.getElementsByTag("availability").text());
                        lapTopTechSpec.setScreenSize(amazonItem.getElementsByTag("screen_size").text());
                        lapTopTechSpec.setMaxScreenResolution(amazonItem.getElementsByTag("max_screen_resolution").text());
                        lapTopTechSpec.setProcessor(amazonItem.getElementsByTag("processor").text());
                        lapTopTechSpec.setRam(amazonItem.getElementsByTag("ram").text());
                        lapTopTechSpec.setHardDrive(amazonItem.getElementsByTag("hard_drive").text());
                        lapTopTechSpec.setGraphicsCoprocessor(amazonItem.getElementsByTag("graphics_coprocessor").text());
                        lapTopTechSpec.setCardDescription(amazonItem.getElementsByTag("card_description").text());
                        lapTopTechSpec.setWirelessType(amazonItem.getElementsByTag("wireless_type").text());
                        lapTopTechSpec.setNumberOfUSB3Ports(amazonItem.getElementsByTag("number_of_usb3_ports").text());
                        lapTop.setTechSpec(lapTopTechSpec);
                        return lapTop;
                    }
                }
            } catch (FileNotFoundException e) {
                logger.error("File "+xmlFilePath+" not found, error message: "+e.getMessage());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }catch (NullPointerException e){
                logger.error(e.getCause());
            } catch (NumberFormatException e){
                logger.error(e.getMessage());
            }
            return lapTop;
        }
}
