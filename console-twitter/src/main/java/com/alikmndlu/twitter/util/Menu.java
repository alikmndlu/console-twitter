package com.alikmndlu.twitter.util;

public class Menu {
    private void lineSeparator() {
        System.out.println();
    }

    public void returnToDashboardAnnouncement() {
        System.out.println("\nBack To Dashboard...");
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
        System.out.println("|  1. Write New Twit            |");
        System.out.println("|  2. View My Twits             |");
        System.out.println("|  3. Edit My Twits             |");
        System.out.println("|  4. View My Comments          |");
        System.out.println("|  5. Edit My Comments          |");
        System.out.println("|  6. Edit Personal Information |");
        System.out.println("|  7. Delete Account            |");
        System.out.println("|  8. LogOut                    |");
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

    public void printEditCommentMenu() {
        lineSeparator();
        System.out.println("           Edit Comment          ");
        System.out.println("+ - - - - - - - - - - - - - - - +");
        System.out.println("|  1. Edit Comment Text         |");
        System.out.println("|  2. Delete Comment            |");
        System.out.println("|  3. Back To Dashboard         |");
        System.out.println("+ - - - - - - - - - - - - - - - +");
    }
}
