package edu.epam.training.textdivision.main.composite;

/**
 * Created by alexey.valiev on 5/26/19.
 */
public class Lexem {

    private String content;
    private Action action;

    public Lexem() {
    }

    public Lexem(String content) {
        this.content = content;
    }

    public Lexem(String content, Action action) {
        this.content = content;
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public enum Action{
        toLEXEM,
        toSENTENCE,
        toPARAGRAPH,
        toTEXT
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return content;
    }
}
