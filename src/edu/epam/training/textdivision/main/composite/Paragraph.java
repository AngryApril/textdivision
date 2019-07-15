package edu.epam.training.textdivision.main.composite;

import java.util.ArrayList;

/**
 * Created by alexey.valiev on 5/26/19.
 */
public class Paragraph extends Lexem{

    private ArrayList<Sentence> sentences;
    private ArrayList<String> stringSentences;

    public Paragraph(String content) {
        super(content);
    }

    public Paragraph(ArrayList<Sentence> sentences) {

        for (Sentence sentence : sentences){
            this.sentences.add(sentence);
        }
    }

    public Paragraph(String content, ArrayList<Sentence> sentences) {
        super(content);
        for (Sentence sentence : sentences){
            this.sentences.add(sentence);
        }
    }

    public Paragraph(String content, Action action, ArrayList<Sentence> sentences) {
        super(content, action);
        for (Sentence sentence : sentences){
            this.sentences.add(sentence);
        }
    }

    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<Sentence> sentences) {
        this.sentences = sentences;
    }

    public void setStringSentences(ArrayList<String> stringSentences) {
        this.stringSentences = stringSentences;
    }

    public ArrayList<String> getStringSentences(){

        return stringSentences;
    }

    @Override
    public String toString() {
        return "Paragraph{" +
                "sentences=" + sentences +
                '}';
    }
}
