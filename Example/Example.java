import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;

public class Example extends JFrame {
    static FileReader fr;
    static BufferedReader br;
    private static void recurseThroughDirectories(File f) {
        File root = new File(f.getAbsolutePath());
        File[] list = root.listFiles();

        if (list == null) return;
        for (File fs : list) {
            if (fs.isDirectory()) {
                recurseThroughDirectories(fs);
                //System.out.println( "Dir:" + fs.getAbsoluteFile() );
            } else {
                try {
                    fr = new FileReader(fs.getAbsolutePath());
                    br = new BufferedReader(fr);
                    if (br.readLine().equals("Hello"))
                        System.out.println("File:" + fs.getAbsoluteFile());
                } catch (FileNotFoundException fnf) {

                } catch (IOException ioe) {

                }
            }
        }
    }

    public static void main(String[] args) {
        Example.recurseThroughDirectories(new File("C:\\Users\\Gaming"));
    }

}