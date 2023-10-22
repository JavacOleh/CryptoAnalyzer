package Operation;

import Mains.Constants;
import Analyzers.FileAnalyzer;
import Interfaces.ConsoleInterface;
import Mains.Encryptor;
import Mains.Logger;

/*
Class created to working with unprotect data

* file_Joiner - returns String which joins array of strings by space, where file_name is file where data about to join
* onOpenFile - Opens data from file, where 'encryptor' - Encryptor.
* onOpenConsole - Opens data from console,
  where 'encryptor' Encryptor, 'c' ConsoleInterface
* OpenWithoutText - Opens data with new text from example,
  where 'encryptor' Encryptor, 'key' is key for Caesar cipher
* OpenWithoutKey_Text - Opens data with new text from example, key according to example
  where 'encryptor' Encryptor
*/

public class OperationsOpen {
    private static final FileAnalyzer fileAnalyzer = FileAnalyzer.getInstance();
    private static final Logger logger = Logger.getInstance();

    public static void onOpenFile(Encryptor encryptor) {
        fileAnalyzer.setStringInFile(Constants.UserOutPut_Protected_file,encryptor.getProtected_text());
        if(!encryptor.getProtected_text().equals(""))
            encryptor.onBruteForce();
        fileAnalyzer.setStringInFile(Constants.UserOutPut_Opened_file,encryptor.getText());
        logger.appendInfo("onOpenFile");
    }

    public static void onOpenConsole(Encryptor encryptor, ConsoleInterface c) {
        logger.appendInfo("onOpenConsole");
        if(encryptor.getProtected_text() == null || encryptor.getText() == null)
            Operations.DoOperations(1,encryptor,c);

        if(encryptor.getKey() <= 0) {
            if(!encryptor.getProtected_text().equals(""))
                encryptor.onBruteForce();
            else
                OpenWithoutKey_Text(encryptor);
        }else {
            if(encryptor.getProtected_text().equals(""))
                OpenWithoutText(encryptor, encryptor.getKey());
            else
                encryptor.onOpen();
        }
    }

    private static void OpenWithoutText(Encryptor encryptor, int key) {
        logger.appendInfo("OpenWithoutText");
        encryptor.onProtect("Text sample for open",key);
        encryptor.onOpen();
    }
    private static void OpenWithoutKey_Text(Encryptor encryptor) {
        logger.appendInfo("OpenWithoutKey_Text");
        String text = "Text sample for open";
        encryptor.onProtect(text,1);
        encryptor.onOpen();
    }
}
