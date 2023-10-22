package Analyzers;

import Mains.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/*
This singleton has methods that helps you work with Files

* getInstance - returns instance of FileAnalyzer
* getLangFileName - returns file directory according to 'text'
* getAllStringsFromFile - returns array of all text from file, where 'file_name' it is name of file with direction
* setStringInFile appends string in file, where 'file_name' it is name of file with direction, where 'text' it is string to set in file
*/

public class FileAnalyzer {

    StringAnalyzer strUtil = StringAnalyzer.getInstance();

    private static FileAnalyzer instance;

    public static FileAnalyzer getInstance() {
        if(instance == null)
            instance = new FileAnalyzer();
        return instance;
    }
    private FileAnalyzer() {}

    public String getLangFileName(String text) {
        String lang = null;
        if (strUtil.IsRepeated(text, Constants.RU))
            lang = Constants.Popular_RU_file;
        else if (strUtil.IsRepeated(text, Constants.EN)) {
            lang = Constants.Popular_EN_file;
        }

        return lang;
    }
    public String[] getAllStringsFromFile(String file_name) {
        List<String> list = null;
        try {
            list = Files.readAllLines(Path.of(file_name));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return list.toArray(String[]::new);
    }
    public void setStringInFile(String file_name, String str) {

        Path path = Path.of(file_name);
        if(!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(FileWriter stringWriter = new FileWriter(new File(file_name))) {
            stringWriter.write(str + "\n");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
