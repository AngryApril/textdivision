package edu.epam.training.textdivision.main.composite;

import java.util.ArrayList;

/**
 * Created by alexey.valiev on 5/26/19.
 */
public class Text extends Lexem {

    private ArrayList<Paragraph> paragraphs;

    public Text(String content) {
        super(content);
    }

    public Text(ArrayList<Paragraph> paragraphs) {

        for (Paragraph paragraph : paragraphs){
            this.paragraphs.add(paragraph);
        }
    }

    public Text(String content, ArrayList<Paragraph> paragraphs) {
        super(content);
        for (Paragraph paragraph : paragraphs){
            this.paragraphs.add(paragraph);
        }
    }

    public Text(String content, Action action, ArrayList<Paragraph> paragraphs) {
        super(content, action);
        for (Paragraph paragraph : paragraphs){
            this.paragraphs.add(paragraph);
        }
    }

    public ArrayList<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(ArrayList<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    @Override
    public String toString() {
        return "Text{" +
                "paragraphs=" + paragraphs +
                '}';
    }
}
