package com.flashcardsApp.controller;

import com.flashcardsApp.model.Word;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ariel on 22/6/2017.
 */
public class DictionaryController {
    private static String JSON_FILE_DIRECTORY ="D:\\Palabras.json";
    private static String XLSX_FILE_DIRECTORY ="D:\\Palabras.xlsx";
    private long n;
    private Object[][]data;
    private String[] header;
    private List<Word> wordList;
    private WordParser wordParser;

    public DictionaryController(){
        n=0;
        data=getRowsData();
        header=getColumnsHeader();
        wordParser=new WordParser();
        try {
            wordList=wordParser.JSONArrayToWordList(getJSONArrayFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getJSONArrayFromFile()throws IOException,ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(JSON_FILE_DIRECTORY));
        JSONArray jsonArray = (JSONArray) obj;
        return jsonArray;
    }

    public String[] getColumnsHeader(){
        String [] columnsHeader={"Nro.","Palabra","Tipo","Significado"};
        return columnsHeader;
    }

    public Object[][] getRowsData(){
        Object[][] data=null;
        int row = 0;
        int columnSize=getColumnsHeader().length;
        JSONObject jsonObject;
        try {
            JSONArray jsonArray= getJSONArrayFromFile();
            data=new Object[jsonArray.size()][columnSize];
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                jsonObject = iterator.next();
                System.out.println(jsonObject);
                data[row][0] = jsonObject.get("numero");
                data[row][1] = jsonObject.get("palabra");
                System.out.println(data[row][1]);
                data[row][2] = jsonObject.get("tipo");
                data[row][3] = jsonObject.get("significado");
                long number = (Long)data[row][0];
                if(n<number){
                    n=number;
                }
                System.out.println(n);
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeWorkbook(XSSFWorkbook workbook){
        File file = new File(XLSX_FILE_DIRECTORY);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeJSONArray(JSONArray jsonArray){
        try{
            BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter(JSON_FILE_DIRECTORY,false));
            bufferedWriter.write(jsonArray.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
        }catch (IOException ioE){
            ioE.getMessage();
            ioE.getStackTrace();
        }
    }
    public void reload(){
        n=0;
        data=getRowsData();
        header=getColumnsHeader();
        wordParser=new WordParser();
        try {
            wordList=wordParser.JSONArrayToWordList(getJSONArrayFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public Word getWord(int i){
        System.out.println(wordList.get(i).getWordName()+"wrer");
        return wordList.get(i);
    }
    public int wordListSize(){
        return  wordList.size();
    }
    public void exportWorkbook(){
        writeWorkbook(wordParser.wordListToXSLX(wordList));
    }
    public void addWord(Word word){
        wordList.add(word);
        saveWordlist();
    }
    public void saveWordlist(){
        writeJSONArray(wordParser.wordListToJSONArray(wordList));
    }
    public long getN() {
        return n;
    }

    public Object[][] getData() {
        return data;
    }

    public String[] getHeader() {
        return header;
    }
}
