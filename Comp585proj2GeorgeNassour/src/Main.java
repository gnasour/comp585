/**
 * Created by George Nassour on 2/21/2018.
 * Initial point of the project
 * Project uses Swing Worker to give the illusion of a multithreaded application
 * In reality, the GUI uses only one thread but swing worker assigns multiple tasks together using
 * the publish() and process() methods.
 */

import java.io.*;
import javax.swing.SwingUtilities;

public class Main {
    static FileReader fr;
    static BufferedReader br;
    private static void recurseThroughDirectories(File f){
        File root = new File( f.getAbsolutePath() );
        File[] list = root.listFiles();

        if (list == null) return;
        for ( File fs : list ) {
            if ( fs.isDirectory() ) {
                recurseThroughDirectories( fs);
                //System.out.println( "Dir:" + fs.getAbsoluteFile() );
            }
            else {
                try{
                    fr = new FileReader(fs.getAbsolutePath());
                    br = new BufferedReader(fr);
                    if(br.readLine().equals("Hello"))
                        System.out.println( "File:" + fs.getAbsoluteFile() );
                }catch(FileNotFoundException fnf){

                }catch (IOException ioe){

                }catch(NullPointerException npe){
                    System.out.println(npe.toString());
                    System.out.print(fs.toString());
                }
            }
        }


            /*File [] fs = f.listFiles();
            for(File s: fs){
                if(s.isDirectory()){
                    recurseThroughDirectories(s);
                }
                else if(s.isFile()){
                    try{
                        fr = new FileReader(s.getAbsolutePath());
                        br = new BufferedReader(fr);
                        if(br.readLine().equals("Hello")){
                            System.out.print("Found");
                        }
                        publish(s.getName());
                    }catch (FileNotFoundException fnf){
                        JOptionPane.showMessageDialog(frame, "File not found");
                    }catch (IOException ioe){
                        JOptionPane.showMessageDialog(frame, "IO error");
                    }
                }
            }*/
    }
    public static void main(String [] args){
        //recurseThroughDirectories(new File("C:\\Users\\Gaming\\"));
        //Using swing utilities class to start assigning multiple tasks to a single thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConverterFrame();
            }
        });
    }
}
