package edu.epam.training.textdivision.main.chain;

import edu.epam.training.textdivision.main.composite.Lexem;
import edu.epam.training.textdivision.main.composite.LexemComposite;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by alexey.valiev on 5/26/19.
 */
public class TextChain implements Chain {

    private static final Logger logger = Logger.getLogger(TextChain.class);
    private static final String collectDelimeter = "\n\n";

    private Chain nextInChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void collect(LexemComposite lexemComposite, ArrayList<String> strings, Lexem request) {

        if(request.getAction().equals(Lexem.Action.toTEXT)){
            StringBuilder text = new StringBuilder();

            for(int i = 0; i < strings.size(); i++){

                text.append(strings.get(i));
                if(i < strings.size()-1){
                    text.append(collectDelimeter);
                }
            }

            logger.info("\n\nCOLLECTING text : " + text);
            lexemComposite.setText(text.toString());
        }
        else {
            nextInChain.collect(lexemComposite, strings, request);
        }
    }

    @Override
    public void parse(LexemComposite lexemComposite, String string, Lexem request) {

        if(request.getAction().equals(Lexem.Action.toTEXT)){
                lexemComposite.setText(string);
            }
        else{
            nextInChain.parse(lexemComposite, string, request);
        }
    }

}
