package edu.epam.training.textdivision.main.chain;

import edu.epam.training.textdivision.main.composite.Lexem;
import edu.epam.training.textdivision.main.composite.LexemComposite;

import java.util.ArrayList;

/**
 * Created by alexey.valiev on 5/26/19.
 */
public interface Chain {

    public void setNextChain(Chain nextChain);

    public void collect(LexemComposite lexemComposite, ArrayList<String> strings, Lexem request);

    public void parse(LexemComposite lexemComposite, String string, Lexem request);
    
    
}
