/**
 * Created by Gaming on 2/4/2018.
 */

import javax.swing.*;

public class Calculator{

    private Calculator(){
        JFrame frame = new CalculatorFrame();
        frame.setTitle("Calculator");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

    }

    public static void main(String [] args){
        Calculator calculator = new Calculator();
    }
}
