package Analyzers;

import Mains.Constants;

/*
This singleton has methods that helps you work with String, Character, StringBuilder

* getInstance - returns instance of StringAnalyzer
* getRegex - returns pattern according to array of 'regexSymbols'
* isSymbolWrong - checks 'checkSymbol' according to array of 'badSymbols', returns true if 'checkSymbol' is bad symbol.
* IndexOf returns index of 'from' which contains 'look'
* IsRepeated returns true if 'Where' contains one or more symbols in 'From'
* hasSubString returns true if 'main' contains one or more Strings of 'subString',if ignoreCase is true, then compare by ignoring UpperCase or LowerCase
*/

public class StringAnalyzer {

    private static StringAnalyzer instance;

    private StringAnalyzer() {}

    public static StringAnalyzer getInstance() {
        if (instance == null)
            instance = new StringAnalyzer();
        return instance;
    }

    public String getRegex(char[] regexSymbols) {
        StringBuilder regexBuilder = new StringBuilder("[");
        for (int i = 0; i < regexSymbols.length; i++) {
            regexBuilder.append("\\").append(regexSymbols[i]);
        }
        regexBuilder.append("]");
        return regexBuilder.toString();
    }

    public boolean isSymbolWrong(char[] badSymbols, char checkSymbol) {
        int count = 0;
        for(int i = 0; i < badSymbols.length; i++) {
            count+= (checkSymbol == badSymbols[i]) ? 1 : 0;

            if(count > 0) break;
        }

        return count > 0;
    }

    public int IndexOf(StringBuilder[] from, String look) {
        int index = -1;
        for (int i = 0; i < from.length; i++) {
            if(from[i].toString().contains(look)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int IndexOf(String from, char look) {
        int index = -1;
        for(int i = 0; i < from.length(); i++) {
            if(from.charAt(i) == look) {
                index = i;
                break;
            }
        }

        return index;
    }
    public boolean IsRepeated(String From, String Where) {
        int i = 0, count = 0;

        boolean Fi = Where.contains(String.valueOf(From.charAt(i)));


        for(; i < From.length(); i++) {
            count = Fi ? count + 1 : count;
            if(count > 0) break;
        }
        return count > 0;
    }
    public boolean hasSubString(String main, String[] subString, boolean ignoreCase) {
        int Repeats = 0;
        boolean check;
        String[] boof = main.split(getRegex(Constants.WrongSymbolsStr));

        for(int i = 0; i < boof.length; i++) {

            for(int j = 0; j < subString.length; j++) {
                check = ignoreCase ? boof[i].equalsIgnoreCase(subString[j]) : boof[i].equals(subString[j]);

                if(check) Repeats++;

                if(Repeats > 0) break;
            }
            if(Repeats > 0) break;
        }

        return Repeats > 0;
    }
}
