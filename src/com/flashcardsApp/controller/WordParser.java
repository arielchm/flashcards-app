package com.flashcardsApp.controller;

import com.flashcardsApp.model.Word;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ariel on 22/6/2017.
 */
public class WordParser {

    private String wordName;
    private String wordClass;
    private String meaning;
    private long number;
    private static String WORD_NAME="palabra";
    private static String WORD_CLASS="tipo";
    private static String MEANING="significado";
    private static String NUMBER="numero";

    public JSONObject wordToJSONObject(Word word){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NUMBER,word.getNumber());
        jsonObject.put(MEANING,word.getMeaning());
        jsonObject.put(WORD_NAME,word.getWordName());
        jsonObject.put(WORD_CLASS,word.getWordClass());

        return jsonObject;
    }
    public JSONArray wordListToJSONArray(List<Word> wordList){
        int n = wordList.size();
        JSONArray jsonArray= new JSONArray();
        JSONObject jsonObject;
        Word word;
        for(int i=0;i<n;i++){
            word=wordList.get(i);
            jsonObject= wordToJSONObject(word);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    public List<Word> JSONArrayToWordList(JSONArray jsonArray){
        List<Word> wordList =new ArrayList<>();
        Word word;
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = iterator.next();
            wordName=jsonObject.get(WORD_NAME).toString();
            wordClass=jsonObject.get(WORD_CLASS).toString();
            meaning=jsonObject.get(MEANING).toString();
            number=(long)jsonObject.get(NUMBER);
            word=new Word(wordName,wordClass,meaning,number);
            wordList.add(word);
        }
        return  wordList;
    }
    public XSSFWorkbook wordListToXSLX(List<Word> wordList){
        int n = wordList.size();
        ExcelFile excelFile = new ExcelFile("Palabras");
        Word word;
        for(int i=0;i<n;i++){
            word=wordList.get(i);
            wordName=word.getWordName();
            wordClass=word.getWordClass();
            meaning=word.getMeaning();
            number=word.getNumber();
            excelFile.addRow(wordName,wordClass,meaning,number);
        }
        XSSFWorkbook xssfWorkbook=excelFile.getWorkbook();
        return xssfWorkbook;
    }

}
