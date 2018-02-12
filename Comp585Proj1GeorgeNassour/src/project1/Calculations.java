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

    protected static void addCommand(String calculatorCommand){
        calculatorQueue.add(calculatorCommand);
    }

    protected static void computeResults(){
        Iterator calcInputs = calculatorQueue.iterator();
        while(calcInputs.hasNext()){
            nextInput = (String) calcInputs.next();
            if(nextInput.equals("+") || nextInput.equals("-")|| nextInput.equals("*")|| nextInput.equals("/")|| nextInput.equals("%")){
                operator = nextInput;
                while(calcInputs.hasNext()){
                    nextInput = (String) calcInputs.next();
                    if(nextInput.equals("+") || nextInput.equals("-")|| nextInput.equals("*")|| nextInput.equals("/")|| nextInput.equals("%")){

                    }
                    else if(nextInput.equals("=")){
                        result = Double.parseDouble(number1) + Double.parseDouble(number2);
                        returnResults();
                    }
                    else{
                        number2 += nextInput;
                    }
                }


            }
            else{
                number1 += nextInput;
            }

        }

    }




    private static void returnResults(){
        if(Math.floor(result) == result){
            System.out.println((int) result);
        }
        calculatorQueue.clear();
        result = 0;
    }
}
