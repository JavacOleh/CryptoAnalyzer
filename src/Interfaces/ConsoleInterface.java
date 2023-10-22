package Interfaces;

import Analyzers.*;
import Mains.Constants;
import Mains.Logger;

import java.util.Scanner;

/*
This class was created to make interface for console

* getAllTextFileInterface - returns all data from each file according to src//LangData
* lookSceneText - returns string of Scene from each file according to src//LangData, 'EndWord' means end of scene, 'begWord' means start of scene
* getSceneText - works same as 'lookSceneText' but for search scene you need only 'Scene_Number'
* Swipe - appends '\n' symbol in console, 'length' times.
* PrintTextByLang - prints text by each lang according to 'getAllTextFileInterface'
* Wait - waits untill user press enter, where 'sc' - is current scanner
* getUserLangFile - returns file name of each language according to basic constructor.
*/

public class ConsoleInterface {
    private final FileAnalyzer fileAnalyzer = FileAnalyzer.getInstance();
    private final StringAnalyzer stringAnalyzer = StringAnalyzer.getInstance();
    private static final ParseAnalyzer parser = ParseAnalyzer.getInstance();
    private static final Logger logger = Logger.getInstance();
    private final StringBuilder[] AllTextInterface;
    private int Scenes_size = 9;
    private String userLangFile;

    public ConsoleInterface() {
        Scanner sc = new Scanner(System.in);
        int lang_number;
        System.out.println(
                "\nEnter only number/Вводите только цыфру" +
                "\nChoose language/выберите язык\n" +
                        "Enter/Введите(цыфру/number):\n" +
                        "1) Russian/Русский\n" +
                        "2) English/Английский");

        lang_number = parser.parseInt(sc);
        if(lang_number == 1)
            userLangFile = Constants.Interface_RU_file;
        else if(lang_number == 2)
            userLangFile = Constants.Interface_EN_file;
        else {
            System.out.println("Ошибка: Вы выбрали не верный язык, запустите программу заново!");
            System.out.println("Error: You chosen incorrect language, rerun application!\n");
            System.exit(0);
        }
        AllTextInterface = getAllTextFileInterface();

        logger.appendInfo("\nEnter only number/Вводите только цыфру" +
                "\nChoose language/выберите язык\n" +
                "Enter/Введите(цыфру/number):\n" +
                "1) Russian/Русский\n" +
                "2) English/Английский");
        logger.appendInfo(String.valueOf(lang_number));
    }

    public StringBuilder[] getAllTextFileInterface() {
        String[] mas = fileAnalyzer.getAllStringsFromFile(userLangFile);
        StringBuilder[] boof = new StringBuilder[mas.length];

        for (int i = 0; i < boof.length; i++) {
            boof[i] = new StringBuilder(mas[i]);
        }
        return boof;
    }

    public String lookSceneText(String EndWord, String begWord) {
        int c;
        StringBuilder scene = new StringBuilder();

        c = stringAnalyzer.IndexOf(AllTextInterface,begWord);

        while(!AllTextInterface[c].toString().contains(EndWord)) {
            scene.append(AllTextInterface[c]).append("\n");
            c++;
        }
        scene.delete(0,begWord.length());

        logger.appendInfo(scene.toString());
        return scene.toString();
    }

    public String getSceneText(int Scene_number) {
        if(Scene_number > 0 && Scene_number <= Scenes_size)
            return lookSceneText((Scene_number+1) + "M", Scene_number + "M");
        else if(Scene_number < 0)
            return lookSceneText("EXIT", "9M");
        else
            return lookSceneText("1M", "");
    }

    public void Swipe(int length) {
        System.out.print("\n".repeat(length));
    }
    public void PrintTextByLang(String EN, String RU) {
        if (getUserLangFile().equals(Constants.Interface_RU_file)) {
            System.out.print(RU);
            logger.appendInfo(RU);
        } else {
            System.out.print(EN);
            logger.appendInfo(EN);
        }
    }
    public void Wait(Scanner sc) {
        PrintTextByLang(
                "\nPress any key to continue.\n",
                "\nНажмите любую клавишу чтобы продолжить.\n"
        );
        sc.nextLine();
    }

    public String getUserLangFile() {
        return userLangFile;
    }

}
