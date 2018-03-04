/**
 * Long task class
 * Same one as in class, search line by line in a text file
 */
//Swing components
import java.util.StringTokenizer;
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
//AWT components
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//Opening files and parsing strings
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;


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
                                System.out.println(line);
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
