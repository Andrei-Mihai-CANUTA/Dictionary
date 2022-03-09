package com.jetbrains;
import java.util.ArrayList;

public class Definition implements Comparable<Definition>{
    public String dict;
    public String dictType;
    public int year;
    public ArrayList<String> text = new ArrayList<>();

    public String getDict() {
        return dict;
    }

    public String getDictType() {
        return dictType;
    }

    public int getYear() {
        return year;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }

    public void addText(String text) {
        this.text.add(text);
    }

    @Override
    public int compareTo(Definition o) {    //implementam functia de sortare
        if (this.year < o.year){
            return -1;
        }
        if (this.year == o.year){
            return 0;
        }
        return 1;
    }
}

