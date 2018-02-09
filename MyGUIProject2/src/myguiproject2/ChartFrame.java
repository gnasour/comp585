/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myguiproject2;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author sevak
 */
public class ChartFrame extends JFrame {
    
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;    
    private static final double INTEREST_RATE = 5;
    private static final double INITIAL_BALANCE = 1000;    
    private static final int CHART_WIDTH = 300;
    private static final int CHART_HEIGHT = 300;
    
    private JLabel label;
    private JTextField text;
    private JButton button;
    private ChartComponent chart;
    private double balance;
    
    public ChartFrame() {
        balance = INITIAL_BALANCE; 
        chart = new ChartComponent(3 * INITIAL_BALANCE); 
        chart.setPreferredSize(new Dimension(CHART_WIDTH, CHART_HEIGHT)); 
        chart.append(INITIAL_BALANCE);
        createTextField(); 
        createButton(); 
        createPanel();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    private void createTextField() {
        label = new JLabel("Interest Rate: ");
        final int FIELD_WIDTH = 10; 
        text = new JTextField(FIELD_WIDTH); 
        text.setText("" + INTEREST_RATE);
    }
    
    class AddInterestListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            double rate = Double.parseDouble(text.getText()); 
            double interest = balance * rate / 100; 
            balance = balance + interest; 
            chart.append(balance);
        }
    }

    private void createButton() {
        button = new JButton("Add Interest");
        ActionListener listener = new AddInterestListener(); 
        button.addActionListener(listener);
    }
    
    private void createPanel() {
        JPanel panel = new JPanel(); 
        panel.add(label); 
        panel.add(text); 
        panel.add(button); 
        panel.add(chart); 
        add(panel);
    }
 
}
