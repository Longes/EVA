package com.rostlab.generationModule;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Longes on 26.08.2016.
 */
public class generationMain {

    public ArrayList<String> accIdList = new ArrayList<>();
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private Path path;

    public void readFileByLine(Path path) throws IOException {
        try (Scanner scanner =  new Scanner(path, ENCODING.name())){
            while (scanner.hasNextLine()){
                accIdList.add(scanner.nextLine());
            }
        }
    }

    public void downloadEntries() {

    }
}
