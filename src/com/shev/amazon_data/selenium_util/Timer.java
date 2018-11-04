package com.shev.amazon_data.selenium_util;

public class Timer {
    public static void waitSeconds(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
