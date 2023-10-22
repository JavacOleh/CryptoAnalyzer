package Operation;

import Interfaces.ConsoleInterface;
import Mains.Encryptor;
import Mains.Logger;

/*
This class created for working with main and operations like Protect, Open, BruteForce etc..
*/

public class Operations
{
    private static final Logger logger = Logger.getInstance();
    public static void DoOperations(int operation_id, Encryptor encryptor, ConsoleInterface c) {
        switch (operation_id) {
            case 1 -> {
                OperationsEncrypt.ProtectWithoutKey_Text(encryptor,c);
                printSuccess(c,": " + encryptor.getProtected_text());
            }
            case 2 -> {
                if(encryptor.getProtected_text().equals("")) {
                    OperationsEncrypt.onProtectConsole(encryptor, c, "", -1);
                    printSuccess(c, ": " + encryptor.getProtected_text());
                }else
                    printSuccess(c,": " + encryptor.getProtected_text());
            }
            case 4-> {
                if(encryptor.getKey() > 0)
                    encryptor.setKey(-1);

                OperationsOpen.onOpenConsole(encryptor,c);
                printSuccess(c,": " + encryptor.getText());
            }
            case 3-> {
                if(encryptor.getProtected_text().equals("")) {
                    OperationsEncrypt.onProtectConsole(encryptor, c, encryptor.getText(), encryptor.getKey());
                }else {
                    encryptor.parseKey(encryptor.getProtected_text());
                    OperationsOpen.onOpenConsole(encryptor, c);
                    printSuccess(c,": " + encryptor.getText());
                }
            }
            case 5 -> {
                if(encryptor.getKey() > 0)
                    encryptor.setKey(-1);

                OperationsOpen.onOpenConsole(encryptor,c);
                printSuccess(c,": " + encryptor.getKey());
            }
            case 6 -> {
                c.getSceneText(6);
            }
            case 7 -> {
                OperationsEncrypt.onProtectFile(encryptor,c);
                printSuccess(c,"!");
            }
            case 8 -> {
                OperationsOpen.onOpenFile(encryptor);
                printSuccess(c,"!");
            }
            case 9 -> c.PrintTextByLang(
                    "Thank for choosing my application.\n"
                            + "Have a good day!\n",
                    "Спасибо что выбрали мою программу.\n"
                            + "Хорошего вам дня!\n"
            );
            default -> c.PrintTextByLang("Error, wrong operation. Try again\n", "Ошибка, не верная операция. Попробуйте ещё раз\n");
        }
        System.out.print(c.getSceneText(operation_id));
    }

    private static void printSuccess(ConsoleInterface c, String text) {
        c.PrintTextByLang("Success","Успешно");
        logger.appendInfo(text);
        System.out.println(text);
    }
}
