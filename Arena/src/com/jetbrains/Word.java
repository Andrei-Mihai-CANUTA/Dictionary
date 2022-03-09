package com.jetbrains;

import java.util.ArrayList;

public class Word implements Comparable<Word>{
    public String word;
    public String word_en;
    public String type;
    public ArrayList<String> singular = new ArrayList<>();
    public ArrayList<String> plural = new ArrayList<>();
    public ArrayList<Definition> definitions = new ArrayList<>();

    public String getWord() {
        return word;
    }

    public String getWord_en() {
        return word_en;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getSingular() {
        return singular;
    }

    public ArrayList<String> getPlural() {
        return plural;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWord_en(String word_en) {
        this.word_en = word_en;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSingular(ArrayList<String> singular) {
        this.singular = singular;
    }

    public void setPlural(ArrayList<String> plural) {
        this.plural = plural;
    }

    public void setDefinitions(ArrayList<Definition> definitions) {
        this.definitions = definitions;
    }

    public void addSingular(String singular) {
        this.singular.add(singular);
    }

    public void addPlural(String plural) {
        this.plural.add(plural);
    }

    public void addDefinitions(Definition definitions) {
        this.definitions.add(definitions);
    }

    @Override
    public int compareTo(Word o) {
        return this.word.compareTo(o.word);    //functia de sortare deja exista pentru string
    }
}
