package com.flashcardsApp.ui;


import com.flashcardsApp.controller.DictionaryController;
import com.flashcardsApp.model.Word;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ariel on 22/6/2017.
 */
public class AddWordPanel extends JPanel{
    private JLabel jLabel;
    private JLabel jLabelNro;
    private JTextField jTextFieldName;
    private JTextArea jTextArea;
    private JCheckBox jCheckBoxNoun;
    private JCheckBox jCheckBoxVerb;
    private JCheckBox jCheckBoxAdv;
    private JCheckBox jCheckBoxAdj;
    private JButton jButtonSave;
    private JPanel jPanel;
    private long n;
    private DictionaryController dictionaryController;


    public AddWordPanel(){
        dictionaryController =new DictionaryController();
        this.n= dictionaryController.getN()+1;
        init();
    }
    public void init() {
        jPanel = new JPanel();
        Dimension size = new Dimension(500,240);
        jPanel.setMaximumSize(size);
        jPanel.setPreferredSize(size);
        jPanel.setMinimumSize(size);
        TitledBorder border = new TitledBorder(new LineBorder(Color.black), "", TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        jPanel.setBorder(border);
        jPanel.setLayout(null);
        drawComponents(jPanel);
        this.add(jPanel);
    }
    private void drawComponents(JPanel jPanel){
        jLabelNro = new JLabel("Nro. "+n);
        jLabelNro.setBounds(20,20,80,25);
        jPanel.add(jLabelNro);
        jLabel = new JLabel("Palabra ");
        jLabel.setBounds(20,50,80,25);
        jPanel.add(jLabel);
        jTextFieldName=new JTextField(20);
        jTextFieldName.setBounds(100,50,160,25);
        jPanel.add(jTextFieldName);
        jLabel = new JLabel("Tipo");
        jLabel.setBounds(20,80,80,25);

        jCheckBoxNoun=new JCheckBox("Nombre");
        jCheckBoxNoun.setBounds(95,80,80,25);
        jPanel.add(jCheckBoxNoun);

        jCheckBoxVerb=new JCheckBox("Verbo");
        jCheckBoxVerb.setBounds(195,80,80,25);
        jPanel.add(jCheckBoxVerb);

        jCheckBoxAdj=new JCheckBox("Adjetivo");
        jCheckBoxAdj.setBounds(95,110,80,25);
        jPanel.add(jCheckBoxAdj);

        jCheckBoxAdv=new JCheckBox("Adverbio");
        jCheckBoxAdv.setBounds(195,110,80,25);
        jPanel.add(jCheckBoxAdv);

        jPanel.add(jLabel);
        jLabel = new JLabel("Significado");
        jLabel.setBounds(20,140,80,25);
        jPanel.add(jLabel);
        jTextArea=new JTextArea("",3,20);
        jTextArea.setBounds(100,140,160,40);
        jPanel.add(jTextArea);

        jButtonSave=new JButton("Guardar");
        jButtonSave.setBounds(100,190,80,25);
        jPanel.add(jButtonSave);
        addActionSave();
    }
    private void addActionSave() {
        jButtonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wordName=jTextFieldName.getText();
                String wordClass="";
                if(jCheckBoxAdj.isSelected()){
                    wordClass=wordCheker(wordClass,"Adjective");
                }
                if(jCheckBoxNoun.isSelected()){
                    wordClass=wordCheker(wordClass,"Noun");
                }
                if(jCheckBoxAdv.isSelected()){
                    wordClass=wordCheker(wordClass,"Adverb");
                }
                if(jCheckBoxVerb.isSelected()){
                    wordClass=wordCheker(wordClass,"Verb");
                }
                String meaning=jTextArea.getText();
                long number=n;
                Word word=new Word(wordName,wordClass,meaning,number);
                dictionaryController.addWord(word);
                n=n+1;
                clean();
            }
        });
    }
    private String wordCheker(String wordClass,String type){
        if(wordClass.isEmpty()){
            wordClass=type;
        }else{
            wordClass=wordClass+", "+type;
        }
        return wordClass;
    }
    private void clean(){
        jLabelNro.setText("Nro. "+n);
        jTextArea.setText("");
        jTextFieldName.setText("");
        jCheckBoxAdv.setSelected(false);
        jCheckBoxAdj.setSelected(false);
        jCheckBoxVerb.setSelected(false);
        jCheckBoxNoun.setSelected(false);

    }

}
