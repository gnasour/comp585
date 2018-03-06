/**
 * Long task class
 * Same one as in class, search line by line in a text file
 */
//Swing components
import java.awt.event.WindowEvent;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
//AWT components
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
//Opening files and parsing strings
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;

class LongTask extends JInternalFrame {

    //GREP RESULT WINDOW
    private static final int RESULT_WIDTH = 58;
    private static final int RESULT_HEIGHT = 10;

    private static LongTask instance = null;

    private JLabel lbl, lbl2, grepLabel, progressLabel;
    private JTextField tf, grepText;
    private JTextArea grepResults;
    private JButton fileBtn, readBtn, grepChoice, cancelButton;
    private JFileChooser fc;
    private String fileName;
    private Task task;
    private JProgressBar progressBar;
    private JFrame frame; // to properly center JDialogFrame
    private Scanner scanner;
    private String line, grepWord = "";
    private StringTokenizer st;


    public static LongTask getInstance(JFrame frame) {
        if(instance == null) {
            instance = new LongTask(frame);
        }
        return instance;
    }

    private void checkIfClosed(){

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
                            if(st.nextToken().equals(grepWord)){
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
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cancel(true);
                }
            });
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
            System.out.println("Done");
            progressBar.setVisible(false);
            progressLabel.setVisible(false);

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
        grepWord = JOptionPane.showInputDialog(this,"Enter a word you want to grep");
        grepText.setText(grepWord);
    }


    //Recurse through the directories until you have found a file
    private LongTask(JFrame frame) {

        super("File Info", false, true, false, false);
        this.frame = frame;
        // init
        tf = new JTextField(35);
        grepText = new JTextField(11);
        grepText.setText("Enter a word to grep");
        grepResults = new JTextArea(RESULT_HEIGHT, RESULT_WIDTH);
        grepResults.setEditable(false);
        grepResults.setBorder(BorderFactory.createLineBorder(Color.black));
        tf.setEditable(false);
        grepText.setEditable(false);
        fileBtn = new JButton("...");
        readBtn = new JButton("Read");
        grepChoice = new JButton("Grep Word");
        cancelButton = new JButton("Cancel");
        lbl = new JLabel("Number of lines: ");
        lbl2 = new JLabel();
        grepLabel = new JLabel("Grep Word");
        progressLabel = new JLabel("Loading: ");
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
        cancelButton.setPreferredSize(new Dimension(80, 20));

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
        upperPanel.add(cancelButton);

        midPanel.add(progressLabel);
        midPanel.add(progressBar);
        progressBar.setVisible(false);
        progressLabel.setVisible(false);
        midPanel.add(grepLabel);
        midPanel.add(grepText);
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
                if (grepWord == null || grepWord.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid word to grep");
                } else {
                    readBtn.setEnabled(false);
                    progressBar.setVisible(true);
                    progressLabel.setVisible(true);
                    grepResults.setText("");
                    task = new Task();
                    task.execute();
                }
            }
        });

        grepChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseWord();
            }
        });





        pack();
        setBounds(25, 25, 800, 300);
        setLocation(50, 50);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }



}
