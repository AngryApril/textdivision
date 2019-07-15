package edu.epam.training.textdivision.main.service;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alexey.valiev on 5/26/19.
 */
public class FileDataWriter {

    private static final Logger logger = Logger.getLogger(FileDataWriter.class);

    private static final String filePath = "output/";

    public void writeDataToFile(String filename, String string){

        filename = filePath + filename;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            writer.write(string);
        }catch (IOException ioe){
            logger.error("Wrong filename", ioe);
        }

    }
}
