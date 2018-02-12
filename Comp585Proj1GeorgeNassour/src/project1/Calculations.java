package project1;

/*
 * Created by Gaming on 2/11/2018.
 * Handles the calculations input by the user and when "=" is pressed, returns the result to be printed by the text area
 */
import java.lang.Math;
import java.util.ArrayDeque;
import java.util.Iterator;

public class Calculations {

    //Calculator acts as a queue
    private static ArrayDeque<String>calculatorQueue = new ArrayDeque<String>();

    //Intermediary variables to calculate the expressions entered from GUI
    private static String number1="", number2="";
    private static String operator="";
    private static String nextInput="";
    private static double result = 0;

    //Add commands to the queue
    protected static void addCommand(String calculatorCommand){
        calculatorQueue.add(calculatorCommand);
    }

    //Find the result of the inputs after the user has clicked "="
    protected static void computeResults(){

        Iterator calcInputs = calculatorQueue.iterator();

        while(calcInputs.hasNext()){

            nextInput = (String) calcInputs.next();
            if(nextInput.equals("+") || nextInput.equals("-")|| nextInput.equals("*")|| nextInput.equals("/")|| nextInput.equals("%")){

                operator = nextInput;
                while(calcInputs.hasNext()){

                    nextInput = (String) calcInputs.next();
                    if(nextInput.equals("+") || nextInput.equals("-")|| nextInput.equals("*")|| nextInput.equals("/")|| nextInput.equals("%")){

                    }//finding an operand in an operand
                    else if(nextInput.equals("=")){

                        switch(operator) {
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
                                result = Double.parseDouble(number1) / Double.parseDouble(number2);
                                returnResults();
                                break;
                            case "%":
                                result = Double.parseDouble(number1) % Double.parseDouble(number2);
                                returnResults();
                                break;
                        }//cases
                    }//ending the loop with =
                    else{
                        number2 += nextInput;
                    }//getting numbers for number2

                }//second while for number2

            }//finding operands in first if
            else{
                number1 += nextInput;
            }//if for number1

        }//first while for number1

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
}
