/**
 * Created by George Nassour on 2/21/2018.
 * Initial point of the project
 * Project uses Swing Worker to give the illusion of a multithreaded application
 * In reality, the GUI uses only one thread but swing worker assigns multiple tasks together using
 * the publish() and process() methods.
 */

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String [] args){
        //Using swing utilities class to start assigning multiple tasks to a single thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConverterFrame();
            }
        });
    }
}
