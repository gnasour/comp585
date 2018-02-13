package project1;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Created by George Nassour on 2/5/2018.
 * Comp 585 GUI
 * Sevak Asadorian
 * Implementation of Action Listener that has the method call a static method in calculator frame to respond to the input
 */
public class ClickListener implements ActionListener{


    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorFrame.buttonClicked(e);
    }



}
