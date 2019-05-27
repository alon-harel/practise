package com.harel.practise.inteviews.islandscounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame {

    public MainPanel() throws HeadlessException {
        init();
    }

    private void init() {

        JButton quitButton = new JButton("Quit");
        JButton createButton = new JButton("Create");
        JPanel buttonPanel = new JPanel(new BorderLayout(8, 8));

        quitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                SeaPanel seaPanel = new SeaPanel();
                seaPanel.setVisible(true);
                dispose();
            }
        });

        getContentPane().add(buttonPanel, BorderLayout.WEST);

        buttonPanel.add(quitButton, BorderLayout.NORTH);
        buttonPanel.add(createButton, BorderLayout.SOUTH);

        setTitle("example");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    public static void main(String[] args) {
        MainPanel mainPanel = new MainPanel();
        mainPanel.setVisible(true);
    }


}

