package com.shev.amazon_data.controller;

import com.shev.amazon_data.service.AmazonServiceItemRetrieve;
import com.shev.amazon_data.service.RegisterService;
import com.shev.amazon_data.service.SearchAmazonService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.openqa.selenium.WebDriver;

import javax.naming.directory.SearchControls;
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
    private TextArea resultRegisterAccount;

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
            resultSearchItemInfo.clear();
            String itemUrl = SearchAmazonService.searchByASIN(asinText.getText());
            AmazonServiceItemRetrieve amazonService = new AmazonServiceItemRetrieve(itemUrl);
            resultSearchItemInfo.appendText(amazonService.getLapTop().toString());
        }
    }

    @FXML
    private void registerAccount(javafx.event.ActionEvent actionEvent){
        if(nameRegText.getText().equals("")||loginRegText.getText().equals("")||passRegText.getText().equals("")){
            resultRegisterAccount.clear();
            resultRegisterAccount.appendText("name, login, password fields must not be empty!");
        }else {
          WebDriver webDriver = RegisterService.registerUser(nameRegText.getText(),loginRegText.getText(), passRegText.getText());
          if(RegisterService.checkIfRegistered(webDriver, nameRegText.getText())){
              resultRegisterAccount.clear();
              resultRegisterAccount.appendText("user "+nameRegText.getText() +" was registered successfully");
              webDriver.quit();
          }else {
              resultRegisterAccount.clear();
              resultRegisterAccount.appendText("user "+nameRegText.getText() +" was not registered try to change login");
              webDriver.quit();
          }
        }
    }





}
