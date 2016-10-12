package com.rostlab.requestModule.mail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Longes on 22.06.2016.
 */
public class PSIPREDparser {

    public String querry = "";
    public String jpred = "";
    public String conf = "";
    private Integer count = 0;

    public void parsePSIPREDMail() throws IOException {
        processLineByLine();
        System.out.print("querry: " + querry + "\njpred: " + jpred + "\nconf: " + conf);
        log("Done.");
    }

    /**
     Constructor.
     @param aFileName full name of an existing, readable file.
     */
    public PSIPREDparser(String aFileName){
        fFilePath = Paths.get(aFileName);
    }


    /** Template method that calls {@link #processLine(String)}.  */
    public void processLineByLine() throws IOException {
        try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
            scanner.nextLine(); // "Your job has completed successfully and the prediction is summarised below"
            scanner.nextLine(); // "new line"
            while (scanner.hasNextLine()){
                processLine(scanner.nextLine());
            }
        }
    }

    /**
     Overridable method for processing lines in different ways.

     The format is:
     "Query: xxx
     Jpred: xxx
     Conf: xxx

     <repeat>"
     */
    protected void processLine(String aLine){
        //use a second Scanner to parse the content of each line
        Scanner scanner = new Scanner(aLine);
        scanner.useDelimiter(": ");
        String value = "";
        if (scanner.hasNext()){
            //assumes the line has a certain structure
            scanner.next(); // Query:
            if (count.equals(0)) {
                querry += scanner.next().trim();
            } else if (count.equals(1)) {
                jpred += scanner.next().trim();
            } else if (count.equals(2)) {
                conf += scanner.next().trim();
            } else {
                count = -1;
            }
            count++;
        }
    }

    // PRIVATE
    private final Path fFilePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private static void log(Object aObject){
        System.out.println(String.valueOf(aObject));
    }

    private String quote(String aText){
        String QUOTE = "'";
        return QUOTE + aText + QUOTE;
    }
}
