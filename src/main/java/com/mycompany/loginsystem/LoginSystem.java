 package com.mycompany.loginsystem;

import java.util.Scanner;
import java.util.regex.Pattern;



public class LoginSystem {

    static Scanner input = new Scanner(System.in);

    

    // Username must contain "_" and be max 5 characters
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password: 8+ chars, 1 capital, 1 number, 1 special char
    public static boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return Pattern.matches(regex, password);
    }

    // SA number: +27 followed by 9 digits
    public static boolean checkCellPhoneNumber(String number) {
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, number);
    }

    // User registration

    public static String registerUser(String username, String password) {

        if (!checkUserName(username)) {
            return " Username must contain '_' and be max 5 characters.";
        }

        if (!checkPasswordComplexity(password)) {
            return " Password must be 8+ characters, include capital, number, special characters.";
        }

        return " User successfully registered!";
    }

    public static boolean loginUser(String username, String password,
                                    String storedUsername, String storedPassword) {
        return username.equals(storedUsername) &&
               password.equals(storedPassword);
    }

    public static String returnLoginStatus(boolean status) {
        return status
                ? " Successfully logged in! Welcome!"
                : " Incorrect username or password.";
    }

    // MAIN PROGRAM

    public static void main(String[] args) {

        String storedUsername = "";
        String storedPassword = "";
        String cellPhone;

        // USERNAME 
        String username;
        while (true) {
            System.out.println("Enter your Username (and must contain a '_' and a max of 5 chars): ");
            username = input.nextLine();

            if (checkUserName(username)) break;

            System.out.println(" Invalid. Example: user_");
        }

        // PASSWORD
        String password;
        while (true) {
            System.out.print("Enter Password (8+, capital, number, special): ");
            password = input.nextLine();

            if (checkPasswordComplexity(password)) break;

            System.out.println(" Invalid. Example: Password1!");
        }

        // REGISTER
        System.out.println(registerUser(username, password));
        storedUsername = username;
        storedPassword = password;

        // PHONE 
        while (true) {
            System.out.print("Enter Cell number +27{9}): ");
            cellPhone = input.nextLine();

            if (checkCellPhoneNumber(cellPhone)) {
                System.out.println(" Cell number added.");
                break;
            }

            System.out.println(" Invalid. Example: +27123456789");
        }

        // LOGIN Program
        
        System.out.println("\n LOGIN ");

        while (true) {
            System.out.print("Username: ");
            String loginUser = input.nextLine();

            System.out.print("Password: ");
            String loginPass = input.nextLine();

            boolean status = loginUser(loginUser, loginPass, storedUsername, storedPassword);

            System.out.println(returnLoginStatus(status));

            if (status) break;
        }
    }
}