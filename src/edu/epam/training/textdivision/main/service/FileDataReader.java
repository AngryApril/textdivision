package edu.epam.training.textdivision.main.service;

import org.apache.log4j.Logger;

//import org.apache.commons.io.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by alexey.valiev on 5/26/19.
 */
public class FileDataReader {

    private static final Logger logger = Logger.getLogger(FileDataReader.class);

    private static final String filePath = "input/"; //path to input folder
    private static final String delimeter = "\\A";

    public Optional<String> readToString(String filename) {

        String text = "";

        try (Scanner scanner = new Scanner(new File(filePath+filename))) {
            scanner.useDelimiter(delimeter);
            if(scanner.hasNext()){
                text = scanner.next();
                return Optional.of(text);
            }

            if(text.equals("")){
                return Optional.empty();
            }

        }
        catch (NoSuchElementException e){
            logger.error(e.getMessage(), e);
            return Optional.empty();
        }
        catch (FileNotFoundException fileNotFoundException){
            logger.error(fileNotFoundException.getMessage(), fileNotFoundException);
            return Optional.empty();
        }

        return Optional.of(text);
    }
}
