package edu.epam.training.textdivision.main.chain;

import edu.epam.training.textdivision.main.composite.Lexem;
import edu.epam.training.textdivision.main.composite.LexemComposite;
import edu.epam.training.textdivision.main.composite.Paragraph;
import edu.epam.training.textdivision.main.composite.Sentence;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by alexey.valiev on 5/26/19.
 */
public class SentenceChain implements Chain {

    private static final Logger logger = Logger.getLogger(SentenceChain.class);

    private static final String collectDelimeter = "\\.";
    private static final String parseDelimeter = "\\.|!|\\? ";

    private Chain nextInChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void collect(LexemComposite lexemComposite, ArrayList<String> strings, Lexem request) {

        if(request.getAction().equals(Lexem.Action.toSENTENCE)){
            String sentence = "";
            for(int i = 0; i < strings.size(); i++){
                sentence = sentence.concat(strings.get(i));
                if(i < strings.size()-1){
                    sentence = sentence.concat(" ");
                }
                else{
                    sentence = sentence.concat(collectDelimeter);
                }
                //logger.info("\n\nCOLLECTING sentence : " + sentence);

            }
        }
        else {
            nextInChain.collect(lexemComposite, strings, request);
        }

    }


    @Override
    public void parse(LexemComposite lexemComposite, String string, Lexem request) {

        if(request.getAction().equals(Lexem.Action.toSENTENCE)){
            //logger.info("\n\nPARSING string : " + string);
            Optional<Paragraph> paragraphOptional = lexemComposite.findParagraph(string);

            if(paragraphOptional.isPresent()){
                ArrayList<String> sentencesList = new ArrayList<>();
                ArrayList<Sentence> sentences = new ArrayList<>();
                String[] strings = string.split(parseDelimeter);

                for(int i = 0; i < strings.length; i++) {
                    if(!strings[i].isEmpty()){
                        sentencesList.add(strings[i]);
                    }
                }

                //logger.info("NUMBER of sentences: " + sentencesList.size());
                Paragraph paragraph = paragraphOptional.get();
                paragraph.setStringSentences(sentencesList);
                lexemComposite.convertStringToSentence(paragraph);
            }

        }
        else {
            nextInChain.parse(lexemComposite, string, request);
        }
    }

}
