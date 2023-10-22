import Analyzers.ParseAnalyzer;
import Mains.Encryptor;
import Mains.Logger;
import Operation.Operations;
import Interfaces.ConsoleInterface;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        Scanner sc = new Scanner(System.in);
        ParseAnalyzer parser = ParseAnalyzer.getInstance();
        ConsoleInterface c = new ConsoleInterface();
        Encryptor encryptor = new Encryptor(c);
        int s = 0;

        c.PrintTextByLang("Welcome!","Добро пожаловать!");

        c.Swipe(5);

        while(s <= 9){
            System.out.print(c.getSceneText(0));

            s = parser.parseInt(sc);

            logger.appendInfo(String.valueOf(s));

            c.Swipe(15);

            Operations.DoOperations(s, encryptor, c);

            c.Wait(sc);
            if(s == 9) break;
        }

        logger.Save();
    }
}