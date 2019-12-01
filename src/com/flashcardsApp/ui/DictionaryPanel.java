package com.flashcardsApp.ui;

import com.flashcardsApp.controller.DictionaryController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ariel on 22/6/2017.
 */
public class DictionaryPanel extends JPanel{

    private JButton jButtonAdd;
    private JButton jButtonExport;
    private JScrollPane scrollPane;
    private JTable jTable;
    private DictionaryController dictionaryController;
    private JPanel cardPanel;
    private JPanel tablePanel;

    final static String TABLE_PANEL = "Table";
    final static String ADD_WORD_PANEL = "AddWord";
    private boolean tableShown=true;

    public DictionaryPanel(){
        super(new BorderLayout());
        dictionaryController =new DictionaryController();
        drawComponents(this);
    }
    public void drawComponents(JPanel jPanel){
        drawCardPanel(jPanel);
        drawButtons(jPanel);
    }

    private void drawButtons(JPanel jPanel){
        JPanel buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new BorderLayout(10,10));
        jButtonAdd =new JButton("Adicionar");
        buttonsPanel.add(jButtonAdd,BorderLayout.EAST);
        jButtonAdd.setToolTipText("Adiciona palabras a la librer\u00eda");
        addActionAdd();

        jButtonExport=new JButton("Exportar");
        buttonsPanel.add(jButtonExport,BorderLayout.WEST);
        jButtonExport.setToolTipText("Exporta la libreria a Excel");
        addActionExport();

        buttonsPanel.setBorder(new EmptyBorder(10,10,10,10));
        jPanel.add(buttonsPanel,BorderLayout.PAGE_END);
    }
    private void drawCardPanel(JPanel jPanel){
        cardPanel =new JPanel(new CardLayout());
        drawTablePanel(cardPanel);
        drawAddWordPanel(cardPanel);
        jPanel.add(cardPanel,BorderLayout.CENTER);
    }
    private void drawTablePanel(JPanel jPanel){
        tablePanel=new JPanel();
        String columnHeader[] = dictionaryController.getHeader();
        Object data[][] = dictionaryController.getData();
        jTable=new JTable(data,columnHeader);
        jTable.setPreferredScrollableViewportSize(new Dimension(500, 240));
        jTable.setFillsViewportHeight(true);
        //scrollPanel
        scrollPane = new JScrollPane(jTable);
        tablePanel.add(scrollPane,BorderLayout.CENTER);
        jPanel.add(tablePanel,TABLE_PANEL);
    }
    private void drawAddWordPanel(JPanel jPanel){
        AddWordPanel addWordPanel =new AddWordPanel();
        jPanel.add(addWordPanel,ADD_WORD_PANEL);
    }
    private void addActionAdd() {
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cardPanel.getLayout());
                if(tableShown){
                    cl.show(cardPanel, ADD_WORD_PANEL);
                    tableShown=false;
                    jButtonAdd.setText("Mostrar Palabras");
                    dictionaryController.reload();
                    reloadTable();
                }else{

                    cl.show(cardPanel, TABLE_PANEL);
                    tableShown=true;
                    jButtonAdd.setText("Adicionar Palabra");
                }
            }
        });
    }
    private void addActionExport() {
        jButtonExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dictionaryController.exportWorkbook();
            }
        });
    }
    private void reloadTable(){
        /*
        jTable.removeRowSelectionInterval(0,dictionaryController.wordListSize()-1);
        String columnHeader[] = dictionaryController.getHeader();
        Object data[][] = dictionaryController.getData();
        jTable=new JTable(data,columnHeader);
        jTable.setPreferredScrollableViewportSize(new Dimension(500, 240));
        jTable.setFillsViewportHeight(true);*/
        jTable.repaint();
    }
}
