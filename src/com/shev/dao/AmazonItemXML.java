package com.shev.dao;

import java.io.File;
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

import com.shev.model.LapTop;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class AmazonItemXML {

        private static final String USER_DIR = System.getProperty("user.dir");
        private static final String SEP = System.getProperty("file.separator");
        private static final String XML_FILE = USER_DIR+SEP+"src"+SEP+"com"+SEP+"shev"+SEP+"data"+SEP+"amazon_item_data.xml";

        public static void addLapTopDataToXML(LapTop lapTop){
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
                        asinAttr.setValue(lapTop.getAsin());
                        item.setAttributeNode(asinAttr);

                        // set type element
                        Element type = doc.createElement("type");
                        type.appendChild(doc.createTextNode("laptop"));
                        item.appendChild(type);

                        //set itemTitle
                        Element productTitle = doc.createElement("product_title");
                        productTitle.appendChild(doc.createTextNode(lapTop.getProductTitle()));
                        item.appendChild(productTitle);

                        //set itemPrice
                        Element price = doc.createElement("cent_price");
                        price.appendChild(doc.createTextNode(String.valueOf(lapTop.getPriceCents())));
                        item.appendChild(price);

                        //set availability
                        Element availability = doc.createElement("availability");
                        availability.appendChild(doc.createTextNode(lapTop.getAvailability()));
                        item.appendChild(availability);

                        //set screenSize
                        Element screenSize = doc.createElement("screen_size");
                        screenSize.appendChild(doc.createTextNode(lapTop.getTechSpec().getScreenSize()));
                        item.appendChild(screenSize);

                        //set maxScreenResolution
                        Element maxScreenResolution = doc.createElement("max_screen_resolution");
                        maxScreenResolution.appendChild(doc.createTextNode(lapTop.getTechSpec().getMaxScreenResolution()));
                        item.appendChild(maxScreenResolution);

                        //set processor
                        Element processor = doc.createElement("processor");
                        processor.appendChild(doc.createTextNode(lapTop.getTechSpec().getProcessor()));
                        item.appendChild(processor);

                        //set ram
                        Element ram = doc.createElement("ram");
                        ram.appendChild(doc.createTextNode(lapTop.getTechSpec().getRam()));
                        item.appendChild(ram);

                        //set hardDrive
                        Element hardDrive = doc.createElement("hard_drive");
                        hardDrive.appendChild(doc.createTextNode(lapTop.getTechSpec().getHardDrive()));
                        item.appendChild(hardDrive);

                        //set graphicsCoprocessor
                        Element graphicsCoprocessor = doc.createElement("graphics_coprocessor");
                        graphicsCoprocessor.appendChild(doc.createTextNode(lapTop.getTechSpec().getGraphicsCoprocessor()));
                        item.appendChild(graphicsCoprocessor);

                        //set graphicsCoprocessor
                        Element cardDescription = doc.createElement("card_description");
                        cardDescription.appendChild(doc.createTextNode(lapTop.getTechSpec().getCardDescription()));
                        item.appendChild(cardDescription);

                        //set wirelessType
                        Element wirelessType = doc.createElement("wireless_type");
                        if(lapTop.getTechSpec().getWirelessType()!=null){
                            wirelessType.appendChild(doc.createTextNode(lapTop.getTechSpec().getWirelessType()));
                            item.appendChild(wirelessType);
                        }

                        //set numberOfUSB3Ports
                        Element numberOfUSB3Ports = doc.createElement("number_of_usb3_ports");
                        numberOfUSB3Ports.appendChild(doc.createTextNode(lapTop.getTechSpec().getNumberOfUSB3Ports()));
                        item.appendChild(numberOfUSB3Ports);

                        // write the content into xml file
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(XML_FILE);
                        transformer.transform(source, result);


                } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                } catch (TransformerConfigurationException e) {
                        e.printStackTrace();
                } catch (TransformerException e) {
                        e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
}
