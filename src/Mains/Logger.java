package Mains;

import Analyzers.FileAnalyzer;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
This SingleTon was created for logging data

* getInstance - returns instance of Logger
* getFile_name - returns name of current Logger file
* getInfo - returns logger data
* appendInfo - appends 'info' to logger data
* Save - saves data in file
*/

public class Logger {
    private final StringBuilder info;
    private final FileAnalyzer fileAnalyzer = FileAnalyzer.getInstance();
    private String file_name;
    private final SimpleDateFormat formatter = new SimpleDateFormat(Constants.Logger_Format);
    private final Calendar calendar = Calendar.getInstance();
    private static Logger instance;


    public static Logger getInstance() {
        if(instance == null)
            instance = new Logger();
        return instance;
    }

    private Logger() {
        info = new StringBuilder();
    }
    public String getFile_name() {
        return file_name;
    }
    public StringBuilder getInfo() {
        return info;
    }
    public void appendInfo(String info) {
        this.info.append(info).append("\n");
    }
    public void Save() {
        file_name = Constants.Logger_direction + formatter.format(calendar.getTime()) + ".txt";
        fileAnalyzer.setStringInFile(file_name, info.toString());
        info.delete(0,info.length());
    }
}
