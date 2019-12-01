package com.flashcardsApp.model;

/**
 * Created by Ariel on 22/6/2017.
 */
public class Word {
    private String wordName;
    private String wordClass;
    private String meaning;
    private long number;

    public Word(String wordName, String wordClass, String meaning, long number) {
        this.wordName = wordName;
        this.wordClass = wordClass;
        this.meaning = meaning;
        this.number = number;
    }

    public String getWordName() {
        return wordName;
    }

    public String getWordClass() {
        return wordClass;
    }

    public String getMeaning() {
        return meaning;
    }

    public long getNumber() {
        return number;
    }
}
