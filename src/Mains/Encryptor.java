package Mains;
/*
This class has methods for encrypt, hack, open texts

* onProtect - protects text, where 'user_text' is text to protect, 'key' - is key for Caesar cipher
* parseKey - sets the key in range of language length from 'text'
* onOpen - opens text, where 'key' is key for Caesar cipher
* onBruteForce - bruteforces key, text
*/

import Analyzers.Analyzer;
import Interfaces.ConsoleInterface;
import Analyzers.FileAnalyzer;
import Analyzers.StringAnalyzer;

import java.util.Scanner;
public class Encryptor
{
    private String protected_text = "";
    private String text = "";
    private int key = -1;
    private static final Logger logger = Logger.getInstance();
    private final Analyzer analyzer = Analyzer.getInstance();
    private final FileAnalyzer fileAnalyzer = FileAnalyzer.getInstance();
    private final StringAnalyzer strUtil = StringAnalyzer.getInstance();

    private ConsoleInterface c = null;
    public Encryptor(ConsoleInterface c) { this.c = c;}

    public void onProtect(String User_text, int key) {
        char[] boof = User_text.toCharArray();

        for(int i = 0; i < User_text.length(); i++) {
            String lang = analyzer.getAlphabetByLang(User_text);

            int fKey_ind = analyzer.getFilteredIndex(key, User_text, User_text.charAt(i), true);

            char newChar = lang.charAt(fKey_ind);

            if(!strUtil.isSymbolWrong(Constants.WrongSymbols,boof[i])) boof[i] = newChar;
        }

        System.out.println();
        protected_text = new String(boof);
    }

    public void parseKey(String User_text) {
        Scanner sc = new Scanner(System.in);

        if(c.getUserLangFile().equals(Constants.Interface_RU_file))
            System.out.println("Введите ключ в диапазоне 1-" + analyzer.getAlphabetByLang(User_text).length());
        else
            System.out.println("Enter key in range of 1-" + analyzer.getAlphabetByLang(User_text).length());
        key = sc.nextInt();

        if(key >= analyzer.getAlphabetByLang(User_text).length() || key <= 0) key = 1;

        logger.appendInfo("parseKey: " + key);
    }

    public void onProtect(String User_text) {

        char[] boof = User_text.toCharArray();

        parseKey(User_text);

        for(int i = 0; i < User_text.length(); i++) {
                String lang = analyzer.getAlphabetByLang(User_text);

                int fKey_ind = analyzer.getFilteredIndex(key, User_text, User_text.charAt(i), true);

                char newChar = lang.charAt(fKey_ind);

                if(!strUtil.isSymbolWrong(Constants.WrongSymbols,boof[i])) boof[i] = newChar;
        }

        System.out.println();
        protected_text = new String(boof);
    }

    public void onOpen() {
        onOpen(this.key);
    }

    public void onOpen(int key) {
        char[] OpenedText = protected_text.toCharArray();
        String lang = analyzer.getAlphabetByLang(protected_text);

        for(int i = 0; i < OpenedText.length; i++) {
            if(!strUtil.isSymbolWrong(Constants.WrongSymbols,protected_text.charAt(i))) {


                int fKey_ind = analyzer.getFilteredIndex(key, protected_text, OpenedText[i], false);

                char newChar = lang.charAt(fKey_ind);

                if (OpenedText[i] != ' ') OpenedText[i] = newChar;
            }
        }
        this.text = new String(OpenedText);
    }
    public void onBruteForce() throws StringIndexOutOfBoundsException{
        String lang = analyzer.getAlphabetByLang(protected_text);
        if(protected_text == null || lang == null)
            throw new StringIndexOutOfBoundsException("protected_text = null OR you used wrong letters(Only Russian and English!)");

        String[] pWords = fileAnalyzer.getAllStringsFromFile(fileAnalyzer.getLangFileName(protected_text));
        int BruteKey = 0, count = 0;

        for(int i = 0; i < lang.length(); i++) {

            BruteKey = i;
            onOpen(i);

            for (int j = 0; j < pWords.length; j++) {

                count+= (strUtil.hasSubString(text,pWords,true)) ? 1 : 0;
                if(count > 0) break;
            }
            if(count > 0) break;

        }

        key = BruteKey;
    }
    public String getProtected_text() {
        return protected_text;
    }

    public void setProtected_text(String protected_text) {
        this.protected_text = protected_text;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
