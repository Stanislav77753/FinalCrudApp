package main.java.com.airtickets.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    private static Logger logger;
    private static File log = new File("src/main/resources/log.txt");
    private Logger(){}

    private static Logger getInstance(){
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }

    public static void printLog(String stringLog){
        try(BufferedWriter out = new BufferedWriter(new FileWriter(log, true))){
            Date date = new Date();
            out.write(date + " - " + stringLog + "\r\n");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
