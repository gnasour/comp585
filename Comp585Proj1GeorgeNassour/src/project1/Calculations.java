package project1;

/*
 * Created by Gaming on 2/11/2018.
 * Handles the calculations input by the user and when "=" is pressed, returns the result to be printed by the text area
 */
import javax.swing.JOptionPane;
import java.lang.Math;
import java.util.ArrayDeque;

public class Calculations {

    //Calculator acts as a queue
    private static ArrayDeque<String>calculatorQueue = new ArrayDeque<String>();

    //Intermediary variables to calculate the expressions entered from GUI
    private static String number1="", number2="";
    private static String operator="";
    private static double result = 0;

    //Check if there are multiple operators in the user input
    private static boolean multipleOperators = false;


    //Add commands to the queue
    protected static void addCommand(String calculatorCommand){
        if(calculatorCommand.equals("C")){
            resetVariables();
            CalculatorFrame.resetGUI();
        }else if(calculatorCommand.equals("<-")){
            if(!(calculatorQueue.peek().equals("+")||calculatorQueue.peek().equals("-")||calculatorQueue.peek().equals("*")||calculatorQueue.peek().equals("/")||calculatorQueue.peek().equals("%"))){
                calculatorQueue.pop();
            }
        }else if(calculatorCommand.equals("x^2")){
            result = Math.pow(result, 2);
            returnResults();
        }else
            calculatorQueue.add(calculatorCommand);
    }

    protected static Number computeResults(){
        while(!calculatorQueue.isEmpty()){
            if(calculatorQueue.peek().equals("+")||calculatorQueue.peek().equals("-")||calculatorQueue.peek().equals("*")||calculatorQueue.peek().equals("/")||calculatorQueue.peek().equals("%")){
                operator = calculatorQueue.pop();
                if(!calculatorQueue.isEmpty()){
                    while(!calculatorQueue.isEmpty()){
                        try{
                        if(calculatorQueue.peek().equals("+")||calculatorQueue.peek().equals("-")||calculatorQueue.peek().equals("*")||calculatorQueue.peek().equals("/")||calculatorQueue.peek().equals("%")){
                            if(multipleOperators){
                                operator = calculatorQueue.pop();
                                number2 = calculatorQueue.pop();
                            }
                            switch(operator){
                                case "+":
                                    result = Double.parseDouble(number1) + Double.parseDouble(number2);
                                    number1 = String.valueOf(result);
                                    number2 = "";
                                    if(!multipleOperators){
                                        multipleOperators = true;
                                    }
                                    break;
                                case "-":
                                    result = Double.parseDouble(number1) - Double.parseDouble(number2);
                                    number1 = String.valueOf(result);
                                    number2 = "";
                                    if(!multipleOperators){
                                        multipleOperators = true;
                                    }
                                    break;
                                case "*":
                                    result = Double.parseDouble(number1) * Double.parseDouble(number2);
                                    number1 = String.valueOf(result);
                                    number2 = "";
                                    if(!multipleOperators){
                                        multipleOperators = true;
                                    }
                                    break;
                                case "/":
                                    result = Double.parseDouble(number1) / Double.parseDouble(number2);
                                    number1 = String.valueOf(result);
                                    number2 = "";
                                    if(!multipleOperators){
                                        multipleOperators = true;
                                    }
                                    break;
                                case "%":
                                    result = Double.parseDouble(number1) % Double.parseDouble(number2);
                                    number1 = String.valueOf(result);
                                    number2 = "";
                                    if(!multipleOperators){
                                        multipleOperators = true;
                                    }
                                    break;
                            }
                        }
                        else if(calculatorQueue.peek().equals("=")){
                            if(multipleOperators){
                                returnResults();
                            }else {
                                switch(operator){
                                    case "+":
                                        result = Double.parseDouble(number1) + Double.parseDouble(number2);
                                        returnResults();
                                        break;
                                    case "-":
                                        result = Double.parseDouble(number1) - Double.parseDouble(number2);
                                        returnResults();
                                        break;
                                    case "*":
                                        result = Double.parseDouble(number1) * Double.parseDouble(number2);
                                        returnResults();
                                        break;
                                    case "/":
                                        if(number2.equals("0")){
                                            JOptionPane.showMessageDialog(null, "Divide by Zero. ERROR! DOES NOT COMPUTE!", "Divide by Zero", JOptionPane.ERROR_MESSAGE);
                                            calculatorQueue.clear();
                                        }
                                        else {
                                            result = Double.parseDouble(number1) / Double.parseDouble(number2);
                                            returnResults();
                                        }
                                        break;
                                    case "%":
                                        result = Double.parseDouble(number1) % Double.parseDouble(number2);
                                        returnResults();
                                        break;
                                }
                            }
                        }
                        else
                            number2 += calculatorQueue.pop();

                    }catch(NumberFormatException nfe){
                            JOptionPane.showMessageDialog(null, "Input a valid sequence to compute.");
                            CalculatorFrame.resetGUI();
                            resetVariables();
                        }
                    }

                }

            }
            else if(calculatorQueue.peek().equals("=")){
                try {
                    result = Double.parseDouble(number1);
                    returnResults();
                }catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "Enter a valid sequence to compute");
                    calculatorQueue.clear();
                }
            }
            else
                number1 += calculatorQueue.pop();
        }
        return returnResults();
    }



    //Returning the results and clearing the variables
    private static Number returnResults(){
        if(Math.floor(result) == result){
            result = (int) result;
        }
        calculatorQueue.clear();
        number1 = number2 ="";
        return result;
    }

    private static void resetVariables(){
        calculatorQueue.clear();
        number1 = number2 = "";
        result = 0;
    }
}
