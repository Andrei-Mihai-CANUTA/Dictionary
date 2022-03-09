package com.jetbrains;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jetbrains.Main.hashMap;

public class Tasks {


    static boolean addWord(Word word, String language){
        for (Word findWord: hashMap.get(language)) {    //cautam cuvantul in lista
            if (findWord.equals(word)) {    //daca gasim cuvantul, returnam false
                return false;
            }
        }
        hashMap.get(language).add(word);    //altfel, il adaugam si returnam false
        return true;
    }

    static boolean removeWord(String word, String language){
        for (Word findWord: hashMap.get(language)) {        //cautam cuvantul in lista
            if (findWord.getWord().equals(word)) {
                hashMap.get(language).remove(findWord);     //daca il gasim, il stergem si returnam true
                return true;
            }
        }
        return false;   //altfel returnam false
    }

    static boolean addDefinitionForWord(String word, String language, Definition definition){
        for(Word findWord: hashMap.get(language)) {     //cautam cuvantul
            if(findWord.getWord().equals(word)){
                for(Definition findDefinition: findWord.definitions){       //cautam definitia
                    if(findDefinition.getDict().equals(definition.dict)){
                        return false;           //daca exista, returnam false
                    }
                }
                findWord.definitions.add(definition);   //altfel il inlocuim si returnam true
                return true;
            }
        }
        return false;
    }

    static boolean removeDefinition(String word, String language, String dictionary){
        for(Word findWord: hashMap.get(language)){              //cautam cuvantul
            if(findWord.getWord().equals(word)){
                for(Definition findDefinition: findWord.definitions){   //cautam definitia
                    if(findDefinition.getDict().equals(dictionary)){
                        findWord.definitions.remove(findDefinition);     //daca exista, o stergem si returnam true
                        return true;
                    }
                }
            }
        }
        return false;   //altfel returnam false
    }

    static String translateWord(String word, String fromLanguage, String toLanguage){

        for(Word findWord: hashMap.get(fromLanguage)){  //cautam cuvantul
            for(String findWord_singular: findWord.singular){   //cautam in lista de singular a cuvantului
                if(findWord_singular.equals(word)){
                    int index = findWord.singular.indexOf(word) ;   //daca il gasim, salvam indexul
                    String translate = findWord.getWord_en();   //salvam traducerea in engleza a cuvantului
                    for(Word findWord2: hashMap.get(toLanguage)){   //cautam traducerea in engleza
                        if(findWord2.getWord_en().equals(translate)){
                            return findWord2.getSingular().get(index);  //returnam traducerea in limba data
                            //din sigular si la indexul salvat
                        }
                    }
                }
            }
            for(String findWord_singular: findWord.plural){     //asemanator cautarii de singular
                if(findWord_singular.equals(word)){
                    int index = findWord.plural.indexOf(word) ;
                    String translate = findWord.getWord_en();
                    for(Word findWord2: hashMap.get(toLanguage)){
                        if(findWord2.getWord_en().equals(translate)){
                            return findWord2.getPlural().get(index);
                        }
                    }
                }
            }
        }
        return null;
    }

    static String translateSentence(String sentence, String fromLanguage, String toLanguage){
        StringBuilder translate = new StringBuilder();  //initializam un string auxiliar
        for(String word: sentence.split(" ")){  //despartim propozitia dupa spatii
            translate.append(translateWord(word, fromLanguage, toLanguage)).append(" ");    //traducem si lipim
            //cuvintele la string
        }
        return translate.toString();    //returnam propozitia tradusa
    }


    static ArrayList<Definition> getDefinitionsForWord(String word, String language){
        ArrayList<Definition> defAux = new ArrayList<>();   //aici vom retine lista de definitii ordonata

        for(Word findWord: hashMap.get(language)){  //cautam cuvantul
            if(findWord.getWord().equals(word)){
                defAux = findWord.definitions;      //initializam defAux
                Collections.sort(defAux);           //sortam defAux si il returnam
                return defAux;
            }
        }
        return null;
    }

    static void exportDictionary(String language) throws IOException {
        ArrayList<Word> defWord = (ArrayList<Word>) hashMap.get(language);
        Collections.sort(defWord);      //ordonam cuvintele

        for (Word findWord: defWord){
            Collections.sort(findWord.definitions);     //ordonam definitiile
        }

        final Type type = new TypeToken<List<Word>>() {}.getType();
        Gson gson = new Gson();
        String aux = gson.toJson(defWord, type);
        FileWriter  fileWriter = new FileWriter("DicNou.json");     //initializam fisierul
        fileWriter.write(aux);  //scriem in fisier
        fileWriter.close();
    }

}
