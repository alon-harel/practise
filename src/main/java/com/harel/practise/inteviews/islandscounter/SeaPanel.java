package com.harel.practise.inteviews.islandscounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeaPanel extends JFrame {
    public SeaPanel() throws HeadlessException {
        init();
    }

    private void init() {

        JButton quitButton = new JButton("Quitttt");
        JButton createButton = new JButton("Createeeee");
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
                System.exit(0);
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
}
