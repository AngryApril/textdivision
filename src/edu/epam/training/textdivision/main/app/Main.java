package edu.epam.training.textdivision.main.app;

import edu.epam.training.textdivision.main.composite.*;
import edu.epam.training.textdivision.main.service.FileDataReader;
import edu.epam.training.textdivision.main.service.FileDataWriter;
import edu.epam.training.textdivision.main.service.LexemSorter;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by alexey.valiev on 5/22/19.
 */
public class Main {


    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args){

        FileDataReader fileDataReader = new FileDataReader();
        Optional<String> optionalText = fileDataReader.readToString("correct.txt");
        String fileContent;
        LexemComposite lexemComposite = new LexemComposite();
        LexemComposite lexemComposite1 = new LexemComposite();
        LexemComposite lexemComposite2 = new LexemComposite();

        Lexem request = new Lexem();
        FileDataWriter fileDataWriter = new FileDataWriter();

        if(!optionalText.isPresent()) return;
        fileContent = optionalText.get();

        //Parse text and create composite objects
        lexemComposite.parse(fileContent);

        logger.info("\nNUMBER of paragraphs: " + lexemComposite.getParagraphs().size());
        for(Paragraph paragraph : lexemComposite.getParagraphs()){
            logger.info("Number of sentences: " + paragraph.getSentences().size());
        }

        //collect the text back from paragraphs
        request.setAction(Lexem.Action.toTEXT);
        lexemComposite.convertParagraphsToString();
        lexemComposite1.collect(lexemComposite.getStringParagraphs(), request);
        fileDataWriter.writeDataToFile("Paragraphs_UNSORTED.txt", lexemComposite1.getText());

        //Sort paragraphs by number of sentences and write them to file
        LexemSorter lexemSorter = new LexemSorter();
        lexemSorter.sortByNumberOfSentences(lexemComposite, lexemComposite.getParagraphs());
        lexemComposite.convertParagraphsToString();
        lexemComposite2.collect(lexemComposite.getStringParagraphs(), request);
        fileDataWriter.writeDataToFile("Paragraphs_SORTED.txt", lexemComposite2.getText());

        logger.info("\nNUMBER of paragraphs: " + lexemComposite.getParagraphs().size());
        for(Paragraph paragraph : lexemComposite.getParagraphs()){
            logger.info("Number of sentences SORTED: " + paragraph.getSentences().size());
        }

    }
}
