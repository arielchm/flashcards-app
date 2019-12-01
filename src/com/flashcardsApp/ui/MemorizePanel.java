package com.flashcardsApp.ui;

import com.flashcardsApp.controller.DictionaryController;
import com.flashcardsApp.model.Word;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ariel on 22/6/2017.
 */
public class MemorizePanel extends JPanel {
    private JLabel jLabelName;
    private JLabel jLabelClass;
    private JLabel jLabelMeaning;

    private JButton jButtonPrevious;
    private JButton jButtonNext;
    private JButton jButtonShow;
    private JButton jButtonRestart;
    private JPanel wordPanel;
    private JPanel meaningPanel;
    private JPanel cardPanel;
    private JPanel mainpanel;

    final static String WORDPANEL = "Word";
    final static String MEANINGPANEL = "Meaning";
    private boolean wordShown=true;
    private int index;
    private int limit;
    DictionaryController dictionaryController;

    public MemorizePanel(){
        super(new BorderLayout());
        dictionaryController =new DictionaryController();
        this.index=0;
        this.limit= dictionaryController.wordListSize();
        mainpanel=new JPanel();
        mainpanel.setLayout(new BorderLayout());
        drawComponents(mainpanel);
        this.add(mainpanel);
    }
    public void drawComponents(JPanel jPanel){
        drawButtons(jPanel);
        drawCardPanel(jPanel, dictionaryController.getWord(0));
    }
    private void drawButtons(JPanel jPanel){
        JPanel bottomPanel=new JPanel();
        bottomPanel.setLayout(new BorderLayout(20,20));
        JPanel topPanel=new JPanel();
        topPanel.setLayout(new BorderLayout(20,20));

        jButtonPrevious=new JButton("Anterior");
        jButtonNext=new JButton("Siguiente");
        jButtonShow=new JButton("Mostrar Reverso");
        jButtonRestart=new JButton("Reiniciar");
        jButtonRestart.setSize(80,25);
        addActionShow();
        addActionNext();
        addActionPrevious();
        addActionRestart();

        topPanel.add(jButtonNext,BorderLayout.EAST);
        topPanel.add(jButtonShow,BorderLayout.CENTER);
        topPanel.add(jButtonPrevious,BorderLayout.WEST);
        bottomPanel.add(jButtonRestart);

        topPanel.setBorder(new EmptyBorder(10,50,10,50));
        bottomPanel.setBorder(new EmptyBorder(10,200,10,200));
        jPanel.add(topPanel,BorderLayout.NORTH);
        jPanel.add(bottomPanel,BorderLayout.SOUTH);

    }
    private void drawCardPanel(JPanel jPanel,Word word){
        cardPanel =new JPanel(new CardLayout());
        String wordName=word.getWordName();
        System.out.println(word.getWordName()+"ss   ");
        String wordClass=word.getWordClass();
        String meaning=word.getMeaning();
        drawNamePanel(cardPanel,wordName);
        drawMeaningPanel(cardPanel,wordName,wordClass,meaning);
        showWordPanel();
        jPanel.add(cardPanel,BorderLayout.CENTER);
    }
    private void drawNamePanel(JPanel jPanel,String wordName){
        wordPanel=new JPanel();
        wordPanel.setLayout(new BorderLayout());
        Font newFont = new Font("SERIF", Font.BOLD, 40);
        jLabelName=new JLabel(wordName);
        jLabelName.setFont(newFont);
        jLabelName.setHorizontalAlignment(SwingConstants.CENTER);
        wordPanel.add(jLabelName,BorderLayout.CENTER);
        wordPanel.setBorder(new EmptyBorder(20,20,20,20));
        jPanel.add(wordPanel,WORDPANEL);
    }
    private void drawMeaningPanel(JPanel jPanel,String wordName,String wordClass,String meaning){
        meaningPanel=new JPanel();
        meaningPanel.setLayout(new BorderLayout());
        Font newFont = new Font("SERIF", Font.BOLD, 25);
        jLabelName=new JLabel(wordName);
        jLabelName.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelName.setVerticalAlignment(SwingConstants.CENTER);
        jLabelName.setFont(newFont);
        meaningPanel.add(jLabelName,BorderLayout.PAGE_START);
        jLabelClass=new JLabel(wordClass);
        jLabelClass.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelClass.setVerticalAlignment(SwingConstants.CENTER);
        jLabelClass.setFont(newFont);
        jLabelMeaning=new JLabel(meaning);
        jLabelMeaning.setAutoscrolls(true);
        jLabelMeaning.setVerticalAlignment(SwingConstants.CENTER);
        jLabelMeaning.setFont(newFont);
        meaningPanel.add(jLabelClass,BorderLayout.CENTER);
        meaningPanel.add(jLabelMeaning,BorderLayout.PAGE_END);
        meaningPanel.setBorder(new EmptyBorder(20,20,20,20));
        jPanel.add(meaningPanel,MEANINGPANEL);
    }

    private void addActionShow() {
        jButtonShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cardPanel.getLayout());
                if(wordShown){
                    cl.show(cardPanel, MEANINGPANEL);
                    wordShown=false;
                    jButtonShow.setText("Mostrar Palabra");
                }else{
                    cl.show(cardPanel, WORDPANEL);
                    wordShown=true;
                    jButtonShow.setText("Mostrar Reverso");
                }
            }
        });
    }
    private void addActionNext() {
        jButtonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index=index+1;
                if(index>limit){
                    showWord(limit);
                    index=limit;
                }else{
                    showWord(index);
                }
            }
        });
    }
    private void addActionPrevious() {
        jButtonPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index=index-1;
                if(index<0){
                    showWord(0);
                    index=0;
                }else{
                    showWord(index);
                }

            }
        });
    }
    private void addActionRestart() {
        jButtonRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWord(0);
                index=0;
            }
        });
    }
    private void showWord(int index){
        drawCardPanel(mainpanel, dictionaryController.getWord(index));
        showWordPanel();
        wordShown=true;
    }
    private void showWordPanel(){
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel, MEANINGPANEL);
        cl.show(cardPanel, WORDPANEL);

    }
}
