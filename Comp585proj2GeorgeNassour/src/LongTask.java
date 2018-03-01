/**
 * Long task class
 * Same one as in class, search line by line in a text file
 */

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.omg.SendingContext.RunTime;

class LongTask extends JInternalFrame {

    private static LongTask instance = null;

    private JLabel lbl, lbl2;
    private JTextField tf;
    private JButton fileBtn, readBtn;
    private JFileChooser fc;
    private String fileName;
    private Task task;
    private JProgressBar progressBar;
    private JFrame frame; // to properly center JDialogFrame
    private int numOfTimeProcessGotCalled;
    private FileReader fr;
    private BufferedReader br;

    public static LongTask getInstance(JFrame frame) {
        if(instance == null) {
            instance = new LongTask(frame);
        }
        return instance;
    }


    class Task extends SwingWorker<Void, String> {
        /*
        * Main task. Executed in background thread.
        */
        private void recurseThroughDirectories(File f){
            File [] fs = f.listFiles();
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
            }
        }

        @Override

        public Void doInBackground() {
            if(fileName.equals("")) {
                JOptionPane.showMessageDialog(frame, "Choose a file!");
                return null;
            }
            progressBar.setIndeterminate(true);
            lbl2.setText("");
            recurseThroughDirectories(new File(fileName));
            /*try {
                int lines = 0;
                String fileLine = "";
                FileReader data = new FileReader(fileName);
                BufferedReader br = new BufferedReader(data);
                while((fileLine = br.readLine()) != null) {
                    System.out.println("Number of lines: " + String.valueOf(lines));
                    lbl2.setText(String.valueOf(lines));
                    publish(String.valueOf(lines));
                    lines++;
                }
                lbl2.setText(String.valueOf(lines));
                // close the file
                br.close();
            }
            catch(FileNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "File not found!");
            }
            catch(IOException ex) {
                JOptionPane.showMessageDialog(frame, "An error occured");
            }*/
            return null;

        }

        @Override
        protected void process(List<String> chunks) {
            // Messages received from the doInBackground() (when invoking the publish() method)
            //System.out.println("in process (called by doInBackground), setting label to: " + String.valueOf(chunks.get(chunks.size()-1)));
            //lbl2.setText(String.valueOf(chunks.get(chunks.size()-1)));
            //System.out.println("in process (called by doInBackground): numOfTimeProcessGotCalled = " + numOfTimeProcessGotCalled);
        }

        /*
        * Executed in event dispatch thread
        */
        @Override
        public void done() {
            progressBar.setIndeterminate(false);
            readBtn.setEnabled(true);
            System.out.println("numOfTimeProcessGotCalled: " + numOfTimeProcessGotCalled);

        }
    }

    private void chooseFile() {
        lbl2.setText("");
        fileName = "";
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            tf.setText(file.getAbsolutePath());
            fileName = file.getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(this, "Open command cancelled by user.");
        }
    }

    //Recurse through the directories until you have found a file


    private LongTask(JFrame frame) {

        super("File Info", false, true, false, false);
        this.frame = frame;
        // init
        tf = new JTextField(50);
        tf.setEditable(false);
        fileBtn = new JButton("...");
        readBtn = new JButton("Read");
        lbl = new JLabel("Number of lines: ");
        lbl2 = new JLabel();
        fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        progressBar = new JProgressBar(0, 100);
        //Call setStringPainted now so that the progress bar height
        //stays the same whether or not the string is shown.
        progressBar.setStringPainted(false);
        fileName = "";

        fileBtn.setPreferredSize(new Dimension(20, 20));
        readBtn.setPreferredSize(new Dimension(80, 20));

        JPanel upperPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        upperPanel.setLayout(new FlowLayout());
        midPanel.setLayout(new FlowLayout());
        lowerPanel.setLayout(new FlowLayout());

        upperPanel.add(tf);
        upperPanel.add(fileBtn);
        upperPanel.add(readBtn);

        midPanel.add(progressBar);

        lowerPanel.add(lbl);
        lowerPanel.add(lbl2);

        add(upperPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);

        // add button listener
        fileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseFile();
            }
        });

        // add button listener
        readBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readBtn.setEnabled(false);
                task = new Task();
                //task.addPropertyChangeListener(this);
                task.execute();
            }
        });

        pack();
        setBounds(25, 25, 700, 120);
        setLocation(50, 50);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
}
