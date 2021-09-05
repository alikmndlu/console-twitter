package com.alikmndlu.twitter;

import com.alikmndlu.twitter.util.ApplicationContext;

public class TwitterApplication {
    public static void main(String[] args) {
        System.out.println("Starting Application");
        ApplicationContext.dataInitializer.init();
        ApplicationContext.layer.authenticateLayer();
        System.out.println("Shutdown Application...");
    }
}
