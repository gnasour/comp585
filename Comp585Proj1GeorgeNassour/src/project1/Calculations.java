package project1;

/**
 * Created by Gaming on 2/11/2018.
 * Handles the calculations input by the user and when "=" is pressed, returns the result to be printed by the text area
 */
import java.lang.Math;
import java.util.ArrayDeque;
import java.util.Iterator;

public class Calculations {

    private static ArrayDeque<String>calculatorQueue = new ArrayDeque<String>();
    double number1, number2, result;
    protected static void addCommand(String calculatorCommand){
        calculatorQueue.add(calculatorCommand);
    }

    protected  static void computeResults(){
        Iterator i = calculatorQueue.iterator();
        while(i.hasNext()){
           if(i == "")
        }
    }


    private static void returnResults(){

    }
}
