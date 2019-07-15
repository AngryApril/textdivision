package edu.epam.training.textdivision.main.chain;

import edu.epam.training.textdivision.main.composite.Lexem;
import edu.epam.training.textdivision.main.composite.LexemComposite;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by alexey.valiev on 5/26/19.
 */
public class ParagraphChain implements Chain {

    private static final Logger logger = Logger.getLogger(ParagraphChain.class);
    private static final String collectDelimeter = "\\.";
    private static final String parseDelimeter = "\\r|\\n";

    private Chain nextInChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void collect(LexemComposite lexemComposite, ArrayList<String> strings, Lexem request) {

        if(request.getAction().equals(Lexem.Action.toPARAGRAPH)){
            String paragraph = "";
            for(int i = 0; i < strings.size(); i++){

                paragraph = paragraph.concat(strings.get(i));
                if(i < strings.size()-1){
                    paragraph = paragraph.concat(". ");
                }
                else{
                    paragraph = paragraph.concat(collectDelimeter);
                }
            }

            logger.info("\n\n" + this.getClass() + "\n\nCOLLECTING paragraph : " + paragraph);
            lexemComposite.addParagraph(paragraph, strings);
        }
        else{
            nextInChain.collect(lexemComposite, strings, request);
        }

    }

    @Override
    public void parse(LexemComposite lexemComposite, String string, Lexem request) {

        if(request.getAction().equals(Lexem.Action.toPARAGRAPH)){

            String[] paragraphs = string.split(parseDelimeter);

            for(int i = 0; i < paragraphs.length; i++){
                if(!paragraphs[i].isEmpty()){
                    lexemComposite.addParagraph(paragraphs[i]);
                }
            }
            logger.info("NUMBER of PARAGRAPHS: " + lexemComposite.getParagraphs().size());

        }
        else {
            nextInChain.parse(lexemComposite, string, request);
        }

    }
}
