package com.alikmndlu.twitter.util;

public class Menu {
    private void lineSeparator() {
        System.out.println();
    }

    public void printAuthenticateMenu() {
        lineSeparator();
        System.out.println("            Main Menu            ");
        System.out.println("+ - - - - - - - - - - - - - - - +");
        System.out.println("|  1. Login                     |");
        System.out.println("|  2. Register                  |");
        System.out.println("|  3. Shutdown Application      |");
        System.out.println("+ - - - - - - - - - - - - - - - +");
    }

    public void printUserDashboardMenu() {
        lineSeparator();
        System.out.println("            Dashboard            ");
        System.out.println("+ - - - - - - - - - - - - - - - +");
        System.out.println("|  1. Edit Personal Information |");
        System.out.println("|  2. Write New Twit            |");
        System.out.println("|  3. View My Twits             |");
        System.out.println("|  4. Edit My Twits             |");
        System.out.println("|  5. LogOut                    |");
        System.out.println("+ - - - - - - - - - - - - - - - +");
    }

    public void printEditPersonalInformationMenu() {
        lineSeparator();
        System.out.println("         Edit Information        ");
        System.out.println("+ - - - - - - - - - - - - - - - +");
        System.out.println("|  1. Edit First Name           |");
        System.out.println("|  2. Edit Last Name            |");
        System.out.println("|  3. Edit Password             |");
        System.out.println("|  4. Back To Dashboard         |");
        System.out.println("+ - - - - - - - - - - - - - - - +");
    }

    public void printEditTwitMenu() {
        lineSeparator();
        System.out.println("            Edit Twit            ");
        System.out.println("+ - - - - - - - - - - - - - - - +");
        System.out.println("|  1. Edit Twit Text            |");
        System.out.println("|  2. Delete Twit               |");
        System.out.println("|  3. Back To Dashboard         |");
        System.out.println("+ - - - - - - - - - - - - - - - +");
    }
}
