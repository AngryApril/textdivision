package edu.epam.training.textdivision.main.composite;

import edu.epam.training.textdivision.main.chain.Chain;
import edu.epam.training.textdivision.main.chain.ParagraphChain;
import edu.epam.training.textdivision.main.chain.SentenceChain;
import edu.epam.training.textdivision.main.chain.TextChain;
import edu.epam.training.textdivision.main.composite.Lexem;
import edu.epam.training.textdivision.main.composite.Paragraph;
import edu.epam.training.textdivision.main.composite.Sentence;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by alexey.valiev on 5/29/19.
 */
public class LexemComposite {

    private static final Logger logger = Logger.getLogger(ParagraphChain.class);

    private ArrayList<Paragraph> paragraphs = new ArrayList<>();
    private ArrayList<String> stringParagraphs = new ArrayList<>();
    private String text;

    public void addParagraph(Paragraph paragraph, ArrayList<Sentence> sentences){
        paragraph.setSentences(sentences);
        paragraphs.add(paragraph);
    }

    public void addParagraph(String paragraph){

        Paragraph paragraph1 = new Paragraph(paragraph);
        paragraphs.add(paragraph1);
        stringParagraphs.add(paragraph);
        paragraph1.setContent(paragraph);
    }

    public void addParagraph(String paragraph, ArrayList<String> sentences){

        Paragraph paragraph1 = new Paragraph(paragraph);
        paragraph1.setStringSentences(sentences);
        paragraphs.add(paragraph1);
        stringParagraphs.add(paragraph);
        paragraph1.setContent(paragraph);
    }

    public Optional<Paragraph> findParagraph(String paragraph){

        for(Paragraph paragraph1 : paragraphs){
            if(paragraph1.getContent().equals(paragraph)){
                return Optional.of(paragraph1);
            }
        }
        logger.info("Paragraph NOT FOUND: " + paragraph);
        return Optional.empty();
    }

    public Optional<Paragraph> findParagraph(Paragraph paragraph){

        for(Paragraph paragraph1 : paragraphs){
            if(paragraph.equals(paragraph1)){
                return Optional.of(paragraph1);
            }
        }
        logger.info("Paragraph NOT FOUND: " + paragraph);
        return Optional.empty();
    }

    public void parse(String text){

        Lexem request = new Lexem();
        Chain textChain = new TextChain();
        Chain paragraphChain = new ParagraphChain();
        Chain sentenceChain = new SentenceChain();

        //Create chain to parse text
        textChain.setNextChain(paragraphChain);
        paragraphChain.setNextChain(sentenceChain);

        request.setAction(Lexem.Action.toPARAGRAPH);
        textChain.parse(this, text, request);
        request.setAction(Lexem.Action.toSENTENCE);
        for(Paragraph paragraph : paragraphs){
            textChain.parse(this, paragraph.getContent(), request);
        }

    }

    public void collect(ArrayList<String> strings, Lexem request){

        Chain sentenceChain = new SentenceChain();
        Chain paragraphChain = new ParagraphChain();
        Chain textChain = new TextChain();

        //Create chain to collect text
        sentenceChain.setNextChain(paragraphChain);
        paragraphChain.setNextChain(textChain);

        sentenceChain.collect(this, strings, request);

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(ArrayList<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public ArrayList<String> getStringParagraphs() {
        return stringParagraphs;
    }

    public void setStringParagraphs(ArrayList<String> stringParagraphs) {
        this.stringParagraphs = stringParagraphs;
    }

    public void convertParagraphsToString(){
        ArrayList<String> strings = new ArrayList<>();
        for(Paragraph paragraph : paragraphs){
            strings.add(paragraph.getContent());
        }
        setStringParagraphs(strings);
    }

    public void convertStringToSentence(Paragraph paragraph){
        if(findParagraph(paragraph).isPresent()){
            ArrayList<Sentence> sentences = new ArrayList<>();
            for(String string : paragraph.getStringSentences()){
                sentences.add(new Sentence(string));
            }
            paragraph.setSentences(sentences);
        }
    }
}
