package com.shev.amazon_data.controller;

import com.shev.amazon_data.service.AmazonServiceItemRetrieve;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class Controller {

    @FXML
    private  TextField linkText;
    @FXML
    private  TextField asinText;
    @FXML
    private RadioButton linkRbutton;
    @FXML
    private RadioButton asinRbutton;
    @FXML
    private Button getDataBatton;
    @FXML
    private TextField nameRegText;
    @FXML
    private TextField loginRegText;
    @FXML
    private TextField passRegText;
    @FXML
    private Button registerButton;
    @FXML
    private TextField loginCartText;
    @FXML
    private TextField asinCartText;
    @FXML
    private Button addToCartButton;
    @FXML
    private TextArea resultSearchItemInfo;

    @FXML
    private void initialize(){
        asinRbutton.setOnAction(event -> linkRbutton.setSelected(false));
        linkRbutton.setOnAction(event -> asinRbutton.setSelected(false));

    }

    @FXML
    private void getItemData(javafx.event.ActionEvent actionEvent){
        if (linkRbutton.isSelected()){
            resultSearchItemInfo.clear();
            AmazonServiceItemRetrieve amazonService = new AmazonServiceItemRetrieve(linkText.getText());
            resultSearchItemInfo.appendText(amazonService.getLapTop().toString());
        }
        if(asinRbutton.isSelected()) {
            System.out.println("asin");
        }
    }




}
