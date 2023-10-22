package Operation;

import Mains.Constants;
import Analyzers.FileAnalyzer;
import Interfaces.ConsoleInterface;
import Mains.Encryptor;
import Mains.Logger;

import java.util.Scanner;

/*
Class created to work with protecting data

* file_Joiner - returns String which joins array of strings by space, where file_name is file where data about to join
* onProtectFile - Protects data from file, where 'encryptor' - Encryptor, 'c' ConsoleInterface.
* onProtectConsole - Protects data from console,
  where 'encryptor' Encryptor, 'c' ConsoleInterface, 'text' - is text about to protect, 'key' is key for Caesar cipher
* ProtectWithoutText - Protects data with new text from console,
  where 'encryptor' Encryptor, 'c' ConsoleInterface, 'key' is key for Caesar cipher
* ProtectWithoutKey_Text - Protects data with new key, text
  where 'encryptor' Encryptor, 'c' ConsoleInterface.
*/

public class OperationsEncrypt {
    private static final FileAnalyzer fileAnalyzer = FileAnalyzer.getInstance();
    private static final Scanner sc = new Scanner(System.in);
    private static final Logger logger = Logger.getInstance();

    public static String file_Joiner(String file_name) {
        String[] input = fileAnalyzer.getAllStringsFromFile(file_name);

        return String.join(" ",input);
    }

    public static void onProtectFile(Encryptor encryptor, ConsoleInterface c) {
        logger.appendInfo("onProtectFile");
        String toProtect = file_Joiner(Constants.UserInput_file);
        String toOpen = file_Joiner(Constants.UserOutPut_Opened_file);


        if(toProtect.equals("")){
            if(!toOpen.equals("")) {
                encryptor.setText(toOpen);
            }else {
                encryptor.setProtected_text("iGNNQ");
                encryptor.onBruteForce();
            }
        }else {
            encryptor.setProtected_text(toProtect);
            encryptor.onBruteForce();
        }
    }
    public static void onProtectConsole(Encryptor encryptor, ConsoleInterface c, String text, int key) {
        logger.appendInfo("ProtectConsole");
        if(text.equals("")) {
                if (key > 0)
                    ProtectWithoutText(encryptor,c,key);
                else
                    ProtectWithoutKey_Text(encryptor,c);
        }else{
                if (key > 0) {
                    encryptor.onProtect(text, key);
                } else {
                    encryptor.onProtect(text);
                }
        }
    }
    private static void ProtectWithoutText(Encryptor encryptor, ConsoleInterface c, int key) {
        String text;
        if(c.getUserLangFile().equals(Constants.Interface_RU_file))
            System.out.println("Вводите ваш текст тут, введите \"nullptr\" для выхода.");
        else
            System.out.println("Enter your text here, use \"nullptr\" to exit.");
        do {
            text = sc.nextLine();
        }while(!sc.nextLine().equalsIgnoreCase("nullptr"));

        encryptor.onProtect(text,key);
        logger.appendInfo("ProtectWithoutText, text: " + text);
    }
    public static void ProtectWithoutKey_Text(Encryptor encryptor, ConsoleInterface c) {
        String text;

        if(c.getUserLangFile().equals(Constants.Interface_RU_file))
            System.out.println("Вводите ваш текст тут, введите \"nullptr\" для выхода.");
        else
            System.out.println("Enter your text here, use \"nullptr\" to exit.");
        do {
            text = sc.nextLine();
        }while(!sc.nextLine().equalsIgnoreCase("nullptr"));

        logger.appendInfo("ProtectWithoutKey_Text, text: " + text);
        encryptor.onProtect(text);
    }
}
