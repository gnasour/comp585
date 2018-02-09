/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author sevak
 */
public class Project1 extends JFrame {
    
    public Project1() {
        
        JPanel keypadPanel = new JPanel(); 
        keypadPanel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,3));
        
        JButton b1 = new JButton("7");
        JButton b2 = new JButton("8");
        JButton b3 = new JButton("9");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("1");
        JButton b8 = new JButton("2");
        JButton b9 = new JButton("3");
        JButton b10 = new JButton("0");
        JButton b11 = new JButton(".");
        JButton b12 = new JButton("C");
        
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        buttonPanel.add(b5);
        buttonPanel.add(b6);
        buttonPanel.add(b7);
        buttonPanel.add(b8);
        buttonPanel.add(b9);
        buttonPanel.add(b10);
        buttonPanel.add(b11);
        buttonPanel.add(b12);
        
        keypadPanel.add(buttonPanel, BorderLayout.CENTER);
        JTextField textField = new JTextField();
        keypadPanel.add(textField, BorderLayout.NORTH);        
        
        // order matters! be careful.
        add(keypadPanel);
        pack();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new Project1();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
