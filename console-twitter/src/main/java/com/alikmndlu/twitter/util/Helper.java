package com.alikmndlu.twitter.util;

public class Helper {
    public int readInteger(String message) {
        while (true) {
            System.out.print(message);

            boolean hasNestInt = ApplicationContext.scanner.hasNextInt();
            if (!hasNestInt) {
                System.out.println("invalid number!");
                ApplicationContext.scanner.nextLine();
                continue;
            }

            int number = ApplicationContext.scanner.nextInt();
            ApplicationContext.scanner.nextLine();
            return number;
        }
    }
}
