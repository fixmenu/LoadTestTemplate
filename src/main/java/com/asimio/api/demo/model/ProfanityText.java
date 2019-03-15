package com.asimio.api.demo.model;

public class ProfanityText {
    private String text;

    public ProfanityText(){

    }

    public ProfanityText(String text){
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{" +
                "\"text\": \""+ text + "\"" +
                '}';
    }
}
