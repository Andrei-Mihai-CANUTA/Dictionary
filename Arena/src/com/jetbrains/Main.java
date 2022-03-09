package com.jetbrains;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Main {
    static Map<String, List<Word>> hashMap;     //definim hashMap

    public static void main(String[] args) throws IOException {
        final Type type = new TypeToken<List<Word>>() {}.getType();
        Gson gson = new Gson();     //initializam gson si hashMap
        hashMap = new HashMap<>();

        File folder = new File("dictionary");   //initializam folderul de fisiere
        File[] listOfFiles = folder.listFiles();        //initializam lista de fisiere
        assert listOfFiles != null;
        for(File file: listOfFiles){
            String fileName = "dictionary//"+file.getName();    //retinem path ul fisierului
            String[] language = file.getName().split("_");  //si limba pe care o folosim ca si key in hashmap
            hashMap.put(language[0],gson.fromJson(new FileReader(fileName), type));     //punem in hashMap
        }


        //task 2 test
        System.out.println("TASK 2");
        Word task2Word = new Word();
        task2Word.setWord("maimuta");
        task2Word.setWord_en("monkey");
        task2Word.addSingular("maimuta");
        task2Word.addPlural("maimute");
        System.out.println(Tasks.addWord(task2Word,"ro"));      //task2Word -> true (nu exista)
        System.out.println(Tasks.addWord(task2Word,"ro"));      //task2Word -> false (exista)


        //task 3 test
        System.out.println("TASK 3");
        System.out.println(Tasks.removeWord("merge","ro"));     //merge -> true (exista)
        System.out.println(Tasks.removeWord("baguette","fr"));      //baguette -> false (nu exista)

        //task 4 test
        System.out.println("TASK 4");
        Definition task4Def = new Definition();
        task4Def.setDict("Dicționar de caini");
        task4Def.setDictType("synonyms");
        task4Def.setYear(1998);
        task4Def.addText("Latrator");
        System.out.println(Tasks.addDefinitionForWord("câine", "ro", task4Def)); //true -> nu exista
        System.out.println(Tasks.addDefinitionForWord("câine", "ro", task4Def));  //false -> exista

        //task 5 test
        System.out.println("TASK 5");   //dam remove de doua ori pe acelasi cuvant -> true false
        System.out.println(Tasks.removeDefinition("câine", "ro", "Dicționar de sinonime"));
        System.out.println(Tasks.removeDefinition("câine", "ro", "Dicționar de sinonime"));

        //task 6 test
        System.out.println("TASK 6");
        System.out.println(Tasks.translateWord("pisică", "ro","fr"));   //singular
        System.out.println(Tasks.translateWord("pisici", "ro","fr"));   //plural

        Word task6Word = new Word();
        task6Word.setWord("mânca");
        task6Word.setWord_en("eat");
        task6Word.addSingular("mănânc");
        task6Word.addSingular("mănânci");
        task6Word.addSingular("mănâncă");
        task6Word.addPlural("mâncăm");
        task6Word.addPlural("mâncați");
        task6Word.addPlural("mănâncă");
        Tasks.addWord(task6Word,"ro");  //adaugam un nou verb


        System.out.println(Tasks.translateWord("mănânci", "ro","fr"));  //verb singular 2
        System.out.println(Tasks.translateWord("mănâncă", "ro","fr"));  //verb plural 3

        //task 7 test
        System.out.println("TASK 7");
        System.out.println(Tasks.translateSentence("mâncăm pisică mâncați pisici","ro", "fr"));

        //task 8 test
        System.out.println("TASK 8");

        //task 9 test
        System.out.println("TASK 9");
        for(Definition definition: Objects.requireNonNull(Tasks.getDefinitionsForWord("câine", "ro"))){
            System.out.println(definition.year + " " + definition.dict);      //am afisat anul si numele dictionarelor

        }

        //task 10 test
        Tasks.exportDictionary("ro");
    }
}

