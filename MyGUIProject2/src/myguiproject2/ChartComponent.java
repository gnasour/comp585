/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myguiproject2;

import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.util.ArrayList; 

/**
 *
 * @author sevak
 */
public class ChartComponent extends JComponent {
    
    private ArrayList<Double> values;
    private double maxValue;

    public ChartComponent(double maxValue) {
        values = new ArrayList<Double>();
        this.maxValue = maxValue;
    }
    
    public void append(double value) {
        values.add(value);
        repaint();
    } 
    
    @Override
    public void paintComponent(Graphics g) {
        final int GAP = 5;
        final int BAR_HEIGHT = 10;
        int y = GAP;
        int barWidth = 0;
        for(double value : values) {
            barWidth = (int) (getWidth() * value / maxValue);
            g.fillRect(0, y, barWidth, BAR_HEIGHT);
            y = y + BAR_HEIGHT + GAP;
        }
    }
}
