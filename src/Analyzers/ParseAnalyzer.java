package Analyzers;

import java.util.Scanner;


/*
This singleton has methods that helps you to validate text

* getInstance - returns instance of ParseAnalyzer
* parseInt returns parsed Int
* parseFloat returns parsed Float
* parseDouble returns parsed Double
*/
public class ParseAnalyzer {

    public static ParseAnalyzer instance;

    private ParseAnalyzer() {}

    public static ParseAnalyzer getInstance() {
        if(instance == null)
            instance = new ParseAnalyzer();
        return instance;
    }

    public int parseInt(Scanner sc) {
        if (sc.hasNextInt()) {
            int result = sc.nextInt();
            sc.nextLine();
            return result;
        } else {
            sc.nextLine();
            return 0;
        }
    }
    public double parseDouble(Scanner sc) {
        if (sc.hasNextDouble()) {
            double result = sc.nextInt();
            sc.nextLine();
            return result;
        } else {
            sc.nextLine();
            return 0;
        }
    }
    public double parseFloat(Scanner sc) {
        if (sc.hasNextFloat()) {
            float result = sc.nextInt();
            sc.nextLine();
            return result;
        } else {
            sc.nextLine();
            return 0;
        }
    }
}
