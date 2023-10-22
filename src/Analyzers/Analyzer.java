package Analyzers;

/*
This singleton has methods that helps you Analyze & working with files

* getInstance - returns instance of Analyzer
* getFilteredKey - returns filtered index of language from 'text', by 'pos' as symbol, according to 'key'.
  if 'protecting' true then that means that we work with protect else open or bruteforce
* getAlphabetByLang returns string of alphabet according to 'text'
* getLangText returns name of each language according to 'text'
*/

import Mains.Constants;

public class Analyzer {

    private static Analyzer instance;
    private Analyzer() {}

    public static Analyzer getInstance() {
        if(instance == null)
            instance = new Analyzer();
        return instance;
    }

    private final StringAnalyzer strUtil = StringAnalyzer.getInstance();
    public int getFilteredIndex(int key, String text, char pos, boolean protecting) {
        int good_key = 0;
        String lang = getAlphabetByLang(text);
        int checkProtect = strUtil.IndexOf(lang,pos) + key;
        int checkOpen = strUtil.IndexOf(lang,pos) - key;

        if(getLangText(text) != null) {
            if(protecting) {
                good_key = checkProtect;
                if(checkProtect > lang.length() - 1) {
                    int diff_lang_pos = (lang.length() - 1) - strUtil.IndexOf(lang,pos);
                    good_key = key - diff_lang_pos - 1;
                }

            } else {
                good_key = checkOpen;
                if(checkOpen < 0) {
                    int diff_pos_key = key - strUtil.IndexOf(lang,pos);
                    good_key = lang.length() - diff_pos_key;
                }
            }

        }
        return good_key;
    }
    public String getAlphabetByLang(String text) {
        if(getLangText(text).equals("RU"))
            return Constants.RU;
        else if (getLangText(text).equals("EN"))
            return Constants.EN;
        return null;
    }
    public String getLangText(String text) {
        if(strUtil.IsRepeated(text, Constants.RU))
            return "RU";
        else if(strUtil.IsRepeated(text, Constants.EN)) {
            return "EN";
        }
        return null;
    }
}
