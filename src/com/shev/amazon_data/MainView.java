package com.shev.amazon_data;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.*;


public class MainView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    File file = new File("");

    @Override
    public void start(Stage primaryStage) throws Exception {

//        primaryStage.widthProperty().addListener((observable, oldValue, newValue) ->
//                System.out.println("resize!!!")
//        );


        Parent root = FXMLLoader.load(getClass().getResource("view/view.fxml"));
        primaryStage.setScene(new Scene(root,500,250));
        primaryStage.setResizable(false);
        primaryStage.setTitle("AmazonTool");
        primaryStage.show();

    }
}