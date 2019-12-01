package com.flashcardsApp.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Ariel on 22/6/2017.
 */
public class MainFrame {
    JFrame frame;
    public void init() {
        frame = new JFrame("FlashCardsApp");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane jTabbedPane = drawTabbedPane();
        jTabbedPane.setOpaque(true);
        frame.setContentPane(jTabbedPane);

        frame.pack();
        frame.setVisible(true);
    }

    public JTabbedPane drawTabbedPane(){
        JTabbedPane jTabbedPane=new JTabbedPane();

        MemorizePanel memorizePanel =new MemorizePanel();
        jTabbedPane.addTab("Memorizar",memorizePanel);

        DictionaryPanel dictionaryPanel =new DictionaryPanel();
        jTabbedPane.addTab("Diccionario", dictionaryPanel);

        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jTabbedPane.getSelectedIndex() == 1){

                }
            }
        });

        return jTabbedPane;
    }
}
