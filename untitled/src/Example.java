import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Example extends JFrame {

    // panels
    private JPanel mainPanel;
    private JPanel buttonPanel;

    // text field
    private JTextField input;

    // buttons
    private JButton b0;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton bDot;
    private JButton ce;

    // menu
    private JMenuBar menuBar;
    private JMenu appMenu;
    private JMenu helpMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem aboutMenuItem;

    public Example() {

        input = new JTextField();

        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        bDot = new JButton(".");
        ce = new JButton("CE");

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,3));

        buttonPanel.add(b9);
        buttonPanel.add(b8);
        buttonPanel.add(b7);
        buttonPanel.add(b6);
        buttonPanel.add(b5);
        buttonPanel.add(b4);
        buttonPanel.add(b3);
        buttonPanel.add(b2);
        buttonPanel.add(b1);
        buttonPanel.add(b0);
        buttonPanel.add(bDot);
        buttonPanel.add(ce);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);

        // **NEW**
        // setup the menu
        menuBar = new JMenuBar();
        // menu
        appMenu = new JMenu("App");
        helpMenu = new JMenu("Help");
        // menu items
        exitMenuItem = new JMenuItem("Exit");
        aboutMenuItem = new JMenuItem("About");
        // add menu items to menu
        appMenu.add(exitMenuItem);
        helpMenu.add(aboutMenuItem);
        // add menus to bar
        menuBar.add(appMenu);
        menuBar.add(helpMenu);
        // menu item listeners
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Thanks for using my app.");
            }
        });
        setJMenuBar(menuBar);

        // **NEW** no need for setSize when using pack(), see javadoc
        pack();
        setTitle("Example");
    }

    public static void main(String[] args) {
        JFrame frame = new Example();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // **NEW**
        frame.setResizable(false);
        frame.setVisible(true);
    }

}