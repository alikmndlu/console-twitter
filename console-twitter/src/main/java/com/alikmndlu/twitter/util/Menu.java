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
        System.out.println("|  2. LogOut                    |");
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
}
