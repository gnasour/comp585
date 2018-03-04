/**
 * Long task class
 * Same one as in class, search line by line in a text file
 */
//Swing components
import java.util.StringTokenizer;
import javax.swing.*;
//AWT components
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//Opening files and parsing strings
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;

class LongTask extends JInternalFrame {

    //GREP RESULT WINDOW
    private static final int RESULT_WIDTH = 50;
    private static final int RESULT_HEIGHT = 10;

    private static LongTask instance = null;

    private JLabel lbl, lbl2;
    private JTextField tf;
    private JTextArea grepResults;
    private JButton fileBtn, readBtn, grepChoice;
    private JFileChooser fc;
    private String fileName;
    private Task task;
    private JProgressBar progressBar;
    private JFrame frame; // to properly center JDialogFrame
    private int numOfTimeProcessGotCalled;
    private Scanner scanner;
    private String line, grepWord;
    private StringTokenizer st;



    public static LongTask getInstance(JFrame frame) {
        if(instance == null) {
            instance = new LongTask(frame);
        }
        return instance;
    }


    class Task extends SwingWorker<Void, String> {
        /*
        * Main task. Executed in background thread.
        *
        */
        private void recurseThroughDirectories(File f){
            File root = new File( f.getAbsolutePath() );
            File[] list = root.listFiles();


            if (list == null) return;
            for ( File fs : list ) {
                if ( fs.isDirectory() ) {
                    recurseThroughDirectories(fs);
                    System.out.println( "Dir:" + fs.getAbsoluteFile() );


                }
                else if (fs.isFile()){
                    System.out.println("File:" + fs.getAbsolutePath());
                    try{

                    scanner = new Scanner(fs);
                    while(scanner.hasNext()){
                        line = scanner.nextLine();
                        st = new StringTokenizer(line);
                        while(st.hasMoreTokens()){
                            if(st.nextToken().equals("Hello")){
                                grepResults.append(line + "\n");
                            }
                        }
                    }


                }catch(FileNotFoundException fnf){
                        System.out.println("The file selected was not found: " + fnf.toString());
                    }
                    catch (NullPointerException npe){
                        System.out.println("Null value while traversing through the files: " + npe.toString());
                    }

                }
            }

        }
        //Executing long task and updating progress bar
        @Override
        public Void doInBackground() {
            if(fileName.equals("")) {
                JOptionPane.showMessageDialog(frame, "Choose a file!");
                return null;
            }
            progressBar.setIndeterminate(true);
            lbl2.setText("");
            recurseThroughDirectories(new File(fileName));
            return null;

        }

        @Override
        protected void process(List<String> chunks) {
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

    //Choosing a directory to descend through
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

    //Choosing a word to grep for
    private void chooseWord(){
        //Task: Create a text box for the user to enter a word to grep for
    }


    //Recurse through the directories until you have found a file
    private LongTask(JFrame frame) {

        super("File Info", false, true, false, false);
        this.frame = frame;
        // init
        tf = new JTextField(35);
        grepResults = new JTextArea(RESULT_HEIGHT, RESULT_WIDTH);
        grepResults.setEditable(false);
        tf.setEditable(false);
        fileBtn = new JButton("...");
        readBtn = new JButton("Read");
        grepChoice = new JButton("Grep Word");
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
        grepChoice.setPreferredSize(new Dimension(110,20));

        JPanel upperPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        upperPanel.setLayout(new FlowLayout());
        midPanel.setLayout(new FlowLayout());
        lowerPanel.setLayout(new FlowLayout());

        upperPanel.add(tf);
        upperPanel.add(fileBtn);
        upperPanel.add(readBtn);
        upperPanel.add(grepChoice);

        midPanel.add(progressBar);
        midPanel.add(grepResults);

        lowerPanel.add(grepResults);
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
                task.execute();
            }
        });

        grepChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseWord();
            }
        });

        pack();
        setBounds(25, 25, 700, 300);
        setLocation(50, 50);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
}
