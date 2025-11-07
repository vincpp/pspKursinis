package com.psp.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    Path logFileLocation;
    int logLevel = 1 ;



    public Logger(Path logFileLocation, int logLevel) {
        this.logFileLocation = logFileLocation;
        this.logLevel = logLevel;

        try{

            if (Files.notExists(logFileLocation)) {
                if (Files.notExists(logFileLocation.getParent())) {
                    Files.createDirectories(logFileLocation.getParent());
                }
                Files.createFile(logFileLocation);
                System.out.println("File created: " + logFileLocation);
            }
            else{
                System.out.println("LogFile already exists.");
            }

        } catch(Exception e){
            System.out.println("An error occurred while setting log file.");
            e.printStackTrace();
        }
    }

    public void logMessage(String message) {
        System.out.println(message);
        if (logLevel > 1) {
            try (FileWriter writer = new FileWriter(logFileLocation.toFile(), true)) {
                String timestamp = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                writer.write(timestamp  + " --- " + message + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
