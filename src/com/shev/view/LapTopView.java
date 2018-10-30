package com.shev.view;

import com.shev.model.LapTopTechSpec;
import com.shev.model.ProductInformation;

public class LapTopView implements ItemViewInterface {
    @Override
    public void printProductDetails(ProductInformation information) {
        if(information.getClass()== LapTopTechSpec.class){
            LapTopTechSpec lapTop = (LapTopTechSpec) information;
            System.out.println(" screenSize:"+lapTop.getScreenSize()+"\n" +
                                "maxScreenResolution:"+lapTop.getMaxScreenResolution()+"\n" +
                                "processor:"+lapTop.getProcessor()+"\n" +
                                "ram:"+lapTop.getRam()+"\n" +
                                "hardDrive:"+lapTop.getHardDrive()+"\n" +
                                "graphicsCoprocessor:"+lapTop.getGraphicsCoprocessor()+"\n" +
                                "cardDescription:"+lapTop.getCardDescription()+"\n" +
                                "wirelessType:"+lapTop.getWirelessType()+"\n" +
                                "numberOfUSB3Ports:"+lapTop.getNumberOfUSB3Ports());
        }


    }
}
