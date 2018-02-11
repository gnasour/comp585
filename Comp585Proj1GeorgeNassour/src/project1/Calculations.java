package project1;

/**
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
    private static double number1, number2, result;
    private static String checkIfOperator;

    protected static void addCommand(String calculatorCommand){
        calculatorQueue.add(calculatorCommand);
    }

    protected  static void computeResults(){
        Iterator calcInputs = calculatorQueue.iterator();
        while(calcInputs.hasNext()){
           checkIfOperator = (String) calcInputs.next();
           if(checkIfOperator == "+"){
               number2 = Double.parseDouble((String) calcInputs.next());
               result = number1 + number2;
               number1 = result;
           }
           else
               number1 = Double.parseDouble(checkIfOperator);
        }
        returnResults();
    }


    private static void returnResults(){
        if(Math.floor(result) == result){
            System.out.println((int) result);
        }
    }
}
