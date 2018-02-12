package project1;

/*
 * Created by Gaming on 2/11/2018.
 * Handles the calculations input by the user and when "=" is pressed, returns the result to be printed by the text area
 */
import javax.swing.*;
import java.lang.Math;
import java.util.ArrayDeque;
import javax.swing.JOptionPane;

public class Calculations {

    //Calculator acts as a queue
    private static ArrayDeque<String>calculatorQueue = new ArrayDeque<String>();
    //The GUI copy to reproduce on the text area
    private static ArrayDeque<String>calculatorQueueGUI;

    //Intermediary variables to calculate the expressions entered from GUI
    private static String number1="", number2="";
    private static String operator="";
    private static double result = 0;


    //Add commands to the queue
    protected static void addCommand(String calculatorCommand){calculatorQueue.add(calculatorCommand);}

    protected static void computeResults(){
        calculatorQueueGUI = calculatorQueue;
        while(!calculatorQueue.isEmpty()){
            if(calculatorQueue.peek().equals("+")||calculatorQueue.peek().equals("-")||calculatorQueue.peek().equals("*")||calculatorQueue.peek().equals("/")||calculatorQueue.peek().equals("%")){
                operator = calculatorQueue.pop();
                if(!calculatorQueue.isEmpty()){
                    while(!calculatorQueue.isEmpty()){
                        try{
                        if(calculatorQueue.peek().equals("+")||calculatorQueue.peek().equals("-")||calculatorQueue.peek().equals("*")||calculatorQueue.peek().equals("/")||calculatorQueue.peek().equals("%")){
                            switch(operator){
                                case "+":
                                    result = Double.parseDouble(number1) + Double.parseDouble(number2);
                                    number1 = String.valueOf(result);
                                    number2 = "";
                                    break;
                                case "-":
                                    result = Double.parseDouble(number1) - Double.parseDouble(number2);
                                    break;
                                case "*":
                                    result = Double.parseDouble(number1) * Double.parseDouble(number2);
                                    break;
                                case "/":
                                    result = Double.parseDouble(number1) / Double.parseDouble(number2);
                                    break;
                                case "%":
                                    result = Double.parseDouble(number1) % Double.parseDouble(number2);
                                    break;
                            }
                        }
                        else if(calculatorQueue.peek().equals("=")){
                            result += Double.parseDouble(number1) + Double.parseDouble(number2);
                            returnResults();
                        }
                        else
                            number2 += calculatorQueue.pop();
                    }catch(NumberFormatException nfe){
                            JOptionPane.showMessageDialog(null, "Input a valid sequence to compute.");
                            resetVariables();
                        }
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "Enter a valid number after the operator");
                }

            }
            else if(calculatorQueue.peek().equals("=")){
                result = Double.parseDouble(number1);
                returnResults();
            }
            else
                number1 += calculatorQueue.pop();
        }
    }



    //Returning the results and clearing the variables
    private static void returnResults(){
        if(Math.floor(result) == result){
            System.out.println((int) result);
        }
        calculatorQueue.clear();
        result = 0;
        number1 = number2 ="";
    }
    private static void resetVariables(){
        calculatorQueue.clear();
        calculatorQueueGUI.clear();
        number1 = number2 = "";
        result = 0;
    }
}
