package com.shev.amazon_data.controller;

import com.shev.amazon_data.model.Item;
import com.shev.amazon_data.model.LapTop;
import com.shev.amazon_data.service.AmazonServiceItemRetrieve;
import com.shev.amazon_data.service.BuyingService;
import com.shev.amazon_data.service.RegisterService;
import com.shev.amazon_data.service.SearchAmazonService;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.openqa.selenium.WebDriver;

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
    private TextField nameRegText;
    @FXML
    private TextField loginRegText;
    @FXML
    private TextField passRegText;
    @FXML
    private TextField loginCartText;
    @FXML
    private TextField passCartText;
    @FXML
    private TextField linkCartText;
    @FXML
    private TextField asinCartText;
    @FXML
    private TextArea resultSearchItemInfo;
    @FXML
    private TextArea resultRegisterAccount;
    @FXML
    private TextArea resultAddItemToCart;
    @FXML
    private RadioButton linkCartRbutton;
    @FXML
    private RadioButton asinCartRbutton;
    @FXML
    private TextField toTime;
    @FXML
    private TextField fromTime;
    @FXML
    private TextArea resultExport;


    @FXML
    private void initialize(){
        asinRbutton.setOnAction(event -> linkRbutton.setSelected(false));
        linkRbutton.setOnAction(event -> asinRbutton.setSelected(false));
        linkCartRbutton.setOnAction(event -> asinCartRbutton.setSelected(false));
        asinCartRbutton.setOnAction(event -> linkCartRbutton.setSelected(false));
    }

    @FXML
    private void getItemData(){
        if (linkRbutton.isSelected()){
            getDataByLink(resultSearchItemInfo, linkText);
        }
        if(asinRbutton.isSelected()) {
            getDataByASIN(resultSearchItemInfo, asinText);

        }
    }

    private static void getDataByASIN(TextArea resultSearchItemInfo, TextField asinText) {
        resultSearchItemInfo.clear();
        String itemUrl = SearchAmazonService.searchByASIN(asinText.getText());
        if(itemUrl!=null){
            AmazonServiceItemRetrieve amazonService = new AmazonServiceItemRetrieve(itemUrl);
            Item item = amazonService.getItem();
            if (item!=null) {
                resultSearchItemInfo.appendText(item.toString().trim());
            }else {
                resultSearchItemInfo.appendText("Yor search did not match any product");
            }
        }else {
            resultSearchItemInfo.appendText("Yor search did not match any product");
        }
    }

    private static void getDataByLink(TextArea resultSearchItemInfo, TextField linkText) {
        resultSearchItemInfo.clear();
        AmazonServiceItemRetrieve amazonService = new AmazonServiceItemRetrieve(linkText.getText());
        Item item = amazonService.getItem();
        if(item!=null){
            resultSearchItemInfo.appendText(item.toString().trim());
        }else {
            resultSearchItemInfo.appendText("link does not match criteria");
        }
    }

    @FXML
    private void registerAccount(){
        if(nameRegText.getText().equals("")||loginRegText.getText().equals("")||passRegText.getText().equals("")){
            resultRegisterAccount.clear();
            resultRegisterAccount.appendText("name, login, password fields must not be empty!");
        }else {
                WebDriver webDriver = RegisterService.registerUser(nameRegText.getText(),loginRegText.getText(), passRegText.getText());
                if (webDriver!=null) {
                        resultRegisterAccount.clear();
                        resultRegisterAccount.appendText("user "+nameRegText.getText() +" was registered successfully");
                        webDriver.quit();
                    }else {
                        resultRegisterAccount.appendText("\nuser "+nameRegText.getText() +" was not registered");
                    }
                }
    }

    @FXML
    private void addToCart(){
        if (linkCartRbutton.isSelected()){
            WebDriver webDriver = BuyingService.addItemToCartByLink(linkCartText.getText(),loginCartText.getText(),passCartText.getText());
            addToCartOutputMessage(webDriver);

        }
        if(asinCartRbutton.isSelected()) {
            WebDriver webDriver = BuyingService.addItemToCartByASIN(asinCartText.getText(),loginCartText.getText(),passCartText.getText());
            addToCartOutputMessage(webDriver);
        }
    }

    private void addToCartOutputMessage(WebDriver webDriver) {
        if(webDriver!=null){
            resultAddItemToCart.clear();
            resultAddItemToCart.appendText("Item was added to cart successfully");
            webDriver.quit();
        }else {
            resultAddItemToCart.clear();
            resultAddItemToCart.appendText("Item was not added to cart. Check your login, password and data");
        }
    }

    @FXML
    private void exportCSV(){
        boolean exportResult = BuyingService.CartReportByPeriodCSV(fromTime.getText(),toTime.getText());
        resultExport.clear();
        if(exportResult){
            resultExport.appendText("CSV file was created to src\\com\\shev\\amazon_data\\data");
        }else {
            resultExport.appendText("CSV file was not created check your data time");
        }

    }

}
