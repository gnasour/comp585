package project1;

/*
 * Created by George Nassour on 2/11/2018.
 * Comp 585 GUI
 * Sevak Asadorian
 * Handles the calculations input by the user and when "=" is pressed, returns the result to be printed on the text area
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
    //In case for functions that require the results from previous computations
    private static double backupResults = 0;

    //Check if there are multiple operators in the user input
    private static boolean multipleOperators = false;


    //Add commands to the queue
    protected static void addCommand(String calculatorCommand){
        if(calculatorCommand.equals("C")){
            resetVariables();
            CalculatorFrame.resetGUI();
        }else if(calculatorCommand.equals("<-")){
            try {
                if(!(calculatorQueue.peek().equals("+")||calculatorQueue.peek().equals("-")||calculatorQueue.peek().equals("*")||calculatorQueue.peek().equals("/")||calculatorQueue.peek().equals("%"))){

                    calculatorQueue.pop();

                }
            }catch(NullPointerException npe){
                System.out.println("The stack was empty when deleting a command");
            }
        }else if(calculatorCommand.equals("x^2")){
            result = backupResults;
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
                                while(!(calculatorQueue.peek().equals("+")||calculatorQueue.peek().equals("-")||calculatorQueue.peek().equals("*")||calculatorQueue.peek().equals("/")||calculatorQueue.peek().equals("%")||calculatorQueue.peek().equals("=")))
                                    number2 += calculatorQueue.pop();
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
                                    if(number2.equals("0")){
                                        JOptionPane.showMessageDialog(null, "Divide by Zero. ERROR! DOES NOT COMPUTE!", "Divide by Zero", JOptionPane.ERROR_MESSAGE);
                                        resetVariables();
                                    }else{
                                        result = Double.parseDouble(number1) / Double.parseDouble(number2);
                                        number1 = String.valueOf(result);
                                        number2 = "";
                                        if(!multipleOperators){
                                            multipleOperators = true;
                                        }
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
                                calculatorQueue.clear();
                            }else {
                                switch(operator){
                                    case "+":
                                        result = Double.parseDouble(number1) + Double.parseDouble(number2);
                                        calculatorQueue.clear();
                                        break;
                                    case "-":
                                        result = Double.parseDouble(number1) - Double.parseDouble(number2);
                                        calculatorQueue.clear();
                                        break;
                                    case "*":
                                        result = Double.parseDouble(number1) * Double.parseDouble(number2);
                                        calculatorQueue.clear();
                                        break;
                                    case "/":
                                        if(number2.equals("0")){
                                            JOptionPane.showMessageDialog(null, "Divide by Zero. ERROR! DOES NOT COMPUTE!", "Divide by Zero", JOptionPane.ERROR_MESSAGE);
                                            resetVariables();
                                        }
                                        else {
                                            result = Double.parseDouble(number1) / Double.parseDouble(number2);
                                            calculatorQueue.clear();
                                        }
                                        break;
                                    case "%":
                                        result = Double.parseDouble(number1) % Double.parseDouble(number2);
                                        calculatorQueue.clear();
                                        break;
                                }
                            }
                        }
                        else
                            number2 += calculatorQueue.pop();

                    }catch(NumberFormatException nfe){
                            JOptionPane.showMessageDialog(null, "Input a valid sequence to compute.");
                            resetVariables();
                            CalculatorFrame.resetGUI();
                        }
                    }

                }

            }
            else if(calculatorQueue.peek().equals("=")){
                try {
                    result = Double.parseDouble(number1);
                    calculatorQueue.clear();
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
        backupResults = result;
        resetVariables();
        return backupResults;
    }

    private static void resetVariables(){
        calculatorQueue.clear();
        number1 = number2 = "";
        operator = "";
        result = 0;
        multipleOperators = false;
    }
}
