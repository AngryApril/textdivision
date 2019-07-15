package edu.epam.training.textdivision.main.service;

import edu.epam.training.textdivision.main.composite.LexemComposite;
import edu.epam.training.textdivision.main.composite.Paragraph;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by alexey.valiev on 5/28/19.
 */
public class LexemSorter {

    private static final Logger logger = Logger.getLogger(LexemSorter.class);

    //public void sortByNumberOfSentences(Map<Paragraph,ArrayList<Sentence>> paragraphMap){}

    public void sortByNumberOfSentences (LexemComposite lexemComposite, ArrayList<Paragraph> paragraphs){

        for(int i = 0; i < paragraphs.size()-1; i++){

            for (int j = 0; j < paragraphs.size()-1; j++){

                if(paragraphs.get(j).getSentences().size() > paragraphs.get(j+1).getSentences().size()){

                    Paragraph paragraph = paragraphs.get(j);
                    paragraphs.set(j, paragraphs.get(j+1));
                    paragraphs.set(j+1, paragraph);
                }

            }
        }

        /*for(Paragraph paragraph : paragraphs){
            logger.info(paragraph.toString());
        }*/
        lexemComposite.setParagraphs(paragraphs);

    }
}
