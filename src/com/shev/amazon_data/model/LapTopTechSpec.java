package com.shev.amazon_data.model;

public class LapTopTechSpec {
    private String screenSize;
    private String maxScreenResolution;
    private String processor;
    private String ram;
    private String hardDrive;
    private String graphicsCoprocessor;
    private String cardDescription;
    private String wirelessType;
    private String numberOfUSB3Ports;

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getMaxScreenResolution() {
        return maxScreenResolution;
    }

    public void setMaxScreenResolution(String maxScreenResolution) {
        this.maxScreenResolution = maxScreenResolution;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }

    public String getGraphicsCoprocessor() {
        return graphicsCoprocessor;
    }

    public void setGraphicsCoprocessor(String graphicsCoprocessor) {
        this.graphicsCoprocessor = graphicsCoprocessor;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getWirelessType() {
        return wirelessType;
    }

    public void setWirelessType(String wirelessType) {
        this.wirelessType = wirelessType;
    }

    public String getNumberOfUSB3Ports() {
        return numberOfUSB3Ports;
    }

    public void setNumberOfUSB3Ports(String numberOfUSB3Ports) {
        this.numberOfUSB3Ports = numberOfUSB3Ports;
    }

    @Override
    public String toString() {
        return  "ScreenSize: "+this.screenSize+"\n" +
                "MaxScreenResolution: "+this.maxScreenResolution+"\n" +
                "Processor: "+this.processor+"\n" +
                "RAM: "+this.ram+"\n" +
                "HardDrive: "+this.hardDrive+"\n" +
                "GraphicsCoprocessor: "+this.graphicsCoprocessor+"\n" +
                "CardDescription: "+this.cardDescription+"\n" +
                "WirelessType: "+this.wirelessType+"\n" +
                "NumberOfUSB3Ports: "+this.numberOfUSB3Ports;
    }

}
