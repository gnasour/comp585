/**
 * Created by George Nassour on 2/5/2018.
 * Provides the frame for the calculator
 *
 * Changelog:
 *
 *
 * Get math operations to the right side of the calculator
 * Completed 2/8/2018
 *
 *
 * Implement mouse and keyboard listeners
 *
 *
 * Add more menu items
 */
package project1;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class CalculatorFrame extends JFrame{

    //Frame dimensions
    private static final int WINDOW_FRAME_WIDTH = 225;
    private static final int WINDOW_FRAME_HEIGHT = 400;

    //JTextArea dimensions
    private static final int CALC_RESULTS_ROWS = 12;
    private static final int CALC_RESULTS_COLUMNS = 5;

    //Panels of the frame
    private JPanel calculatorPanel;
    private JPanel numberPanel;
    private JPanel zeroPanel = new JPanel(new BorderLayout());

    //Text Area of result and history
    private JTextArea calcResults;

    //Menu bar
    private JMenuBar menuBar;
    private JMenu app;
    private JMenu help;
    private JMenuItem exit;
    private JMenuItem about;

    //Listener objects
    private KeyListener keyListener;

    //Calculator Buttons
    private JButton numberZeroButton;
    private JButton numberOneButton;
    private JButton numberTwoButton;
    private JButton numberThreeButton;
    private JButton numberFourButton;
    private JButton numberFiveButton;
    private JButton numberSixButton;
    private JButton numberSevenButton;
    private JButton numberEightButton;
    private JButton numberNineButton;
    private JButton numberDecimalButton;
    private JButton numberClearButton;
    private JButton numberClearEntryButton;
    private JButton numberBackSpace;
    private JButton addButton;
    private JButton multiplyButton;
    private JButton subtractButton;
    private JButton divisionButton;
    private JButton modButton;
    private JButton equalButton;

    //Constructing the components of the calculator
    public CalculatorFrame(){
        createKeyListener();
        initializeMainPanel();
        initializeButtons();
        initializeButtonPanel();
        initializeTextArea();
        initializeMenuBar();
        createFrame();
        setSize(WINDOW_FRAME_WIDTH, WINDOW_FRAME_HEIGHT);
    }

    private void createFrame(){
        setJMenuBar(menuBar);
        add(calculatorPanel);
    }

    //Initializes the main panel of the calculator
    private void initializeMainPanel(){
        calculatorPanel = new JPanel(new BorderLayout());
    }

    //Initialize all buttons for the calculator
    private void initializeButtons(){
        numberZeroButton = new JButton("0");
        numberOneButton = new JButton("1");
        numberTwoButton = new JButton("2");
        numberThreeButton = new JButton("3");
        numberFourButton = new JButton("4");
        numberFiveButton = new JButton("5");
        numberSixButton = new JButton("6");
        numberSevenButton = new JButton("7");
        numberEightButton = new JButton("8");
        numberNineButton = new JButton("9");
        numberDecimalButton = new JButton(".");
        numberClearButton = new JButton("C");
        numberClearEntryButton = new JButton("CE");
        numberBackSpace = new JButton("<-");
        addButton = new JButton("+");
        multiplyButton = new JButton("*");
        subtractButton = new JButton("-");
        divisionButton = new JButton("/");
        modButton = new JButton("%");
        equalButton = new JButton("=");
        ActionListener listener = new ClickListener();
        equalButton.addActionListener(listener);
        multiplyButton.addActionListener(listener);
    }

    //Initialize the button panel and add all the calculator buttons to it
    //Add to the south side of the main panel through border layout
    private void initializeButtonPanel(){
        numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(5,4,10,3));
        numberPanel.add(numberSevenButton);
        numberPanel.add(numberEightButton);
        numberPanel.add(numberNineButton);
        numberPanel.add(addButton);
        numberPanel.add(numberFourButton);
        numberPanel.add(numberFiveButton);
        numberPanel.add(numberSixButton);
        numberPanel.add(subtractButton);
        numberPanel.add(numberOneButton);
        numberPanel.add(numberTwoButton);
        numberPanel.add(numberThreeButton);
        numberPanel.add(multiplyButton);
        numberPanel.add(numberDecimalButton);
        numberPanel.add(numberZeroButton);
        numberPanel.add(numberBackSpace);
        numberPanel.add(divisionButton);
        numberPanel.add(modButton);
        numberPanel.add(numberClearButton);
        numberPanel.add(numberClearEntryButton);
        numberPanel.add(equalButton);
        calculatorPanel.add(numberPanel, BorderLayout.SOUTH);
    }

    //Create a text area for the results of the operations
    private void initializeTextArea(){
        calcResults = new JTextArea(CALC_RESULTS_ROWS, CALC_RESULTS_COLUMNS);
        calcResults.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(calcResults);
        calculatorPanel.add(scrollPane, BorderLayout.NORTH);
    }

    //Create the menu bar at the top of the calculator
    private void initializeMenuBar(){
        menuBar = new JMenuBar();
        app = new JMenu("App");
        help = new JMenu("Help");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("About");
        app.add(exit);
        help.add(about);
        menuBar.add(app);
        menuBar.add(help);
    }

    private void createKeyListener() {
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                System.out.println("keyPressed: " + ke.getKeyCode());

            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }

        };
    }

    protected static void buttonClicked(ActionEvent ae){
        System.out.println(ae.paramString());
    }

}
