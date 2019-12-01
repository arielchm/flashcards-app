package com.flashcardsApp.controller;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by Ariel on 22/6/2017.
 */
public class ExcelFile {

    private String sheetName;
    private XSSFSheet spreadsheet;
    private XSSFWorkbook workbook;
    private int row;

    public ExcelFile(String sheetName) {
        this.sheetName = sheetName;
        this.row = 0;
        init();
        addHeaders();
    }

    public void init() {
        workbook = new XSSFWorkbook();
        spreadsheet = workbook.createSheet(sheetName);
    }

    public void addHeaders() {
        XSSFRow xssfRow = spreadsheet.createRow(row);
        row++;
        xssfRow.createCell(0).setCellValue("Nro.");
        xssfRow.createCell(1).setCellValue("Palabra");
        xssfRow.createCell(2).setCellValue("Tipo");
        xssfRow.createCell(3).setCellValue("Significado");
    }

    public void addRow(String wordName, String wordClass, String meaning, long number) {
        XSSFRow xssfRow = spreadsheet.createRow(row);
        row++;
        xssfRow.createCell(0).setCellValue(number);
        xssfRow.createCell(1).setCellValue(wordName);
        xssfRow.createCell(2).setCellValue(wordClass);
        xssfRow.createCell(3).setCellValue(meaning);
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }
}
