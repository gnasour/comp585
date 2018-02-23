/**
 * Created by Gaming on 2/21/2018.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

class ConverterFrame extends JFrame {

    private JDesktopPane desktop;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JSplitPane splitPane;
    private JPanel labelPanel;
    private JLabel statusLabel;
    private JTree tree;

    //Drop down menu
    private JMenuBar menuBar;
    private JMenu app;
    private JMenu help;
    private JMenuItem exit;
    private JMenuItem about;

    //Tree nodes of the different options
    private DefaultMutableTreeNode fastTasks;

    //Math conversions
    private DefaultMutableTreeNode math;
    private DefaultMutableTreeNode circle;
    private DefaultMutableTreeNode degreeToRadians;
    private DefaultMutableTreeNode fractionToDecimal;

    //Metric conversions
    private DefaultMutableTreeNode poundToKilograms;
    private DefaultMutableTreeNode mphToKPH;
    private DefaultMutableTreeNode 


    private DefaultTreeModel treeModel;

    public ConverterFrame(){initComponenets();}

    //Initialize the basic components with values
    private void initComponenets(){
        buildDesktop();
        initTree();
        addTreeListeners();
        initMenu();
        addMenuListeners();
        initPanel();
        buildFrame();
    }

    //Initialize the tree nodes and add them to a tree
    private void initTree(){
        fastTasks = new DefaultMutableTreeNode("Conversions");
        math = new DefaultMutableTreeNode("Math");
        circle = new DefaultMutableTreeNode("Area of Circle");
        degreeToRadians = new DefaultMutableTreeNode("Degree to Radians");
        fractionToDecimal = new DefaultMutableTreeNode("Fraction to Decimal");
        math.add(circle);
        math.add(degreeToRadians);
        math.add(fractionToDecimal);
        fastTasks.add(math);
        treeModel = new DefaultTreeModel(fastTasks);
        tree = new JTree(treeModel);
    }

    private void initMenu() {
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

    private void initPanel(){
        panel = new JPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tree);

        // label panel and label
        labelPanel = new JPanel();
        statusLabel = new JLabel();
        statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        statusLabel.setMinimumSize(new Dimension(0,18));
        statusLabel.setPreferredSize(new Dimension(0,18));

        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(200);
        splitPane.setContinuousLayout(true);
        splitPane.add(scrollPane, JSplitPane.LEFT);
        splitPane.add(desktop, JSplitPane.RIGHT);

        panel.setLayout(new BorderLayout());
        panel.add(splitPane, BorderLayout.CENTER);

        labelPanel.setLayout(new BorderLayout());
        labelPanel.add(statusLabel, BorderLayout.CENTER);
    }

    private void buildDesktop() {
        desktop = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                ImageIcon imageIcon = new ImageIcon("../images/csun_logo.png");
                Image image = imageIcon.getImage();

                int x=0;
                int y=0;
                double imageWidth = image.getWidth(null);
                double imageHeight = image.getHeight(null);
                double screenWidth = getWidth();
                double screenHeight = getHeight();

                if(screenWidth != 0) {
                    x = (int) screenWidth / 2 - (int) imageWidth / 2;
                }
                if(screenHeight != 0) {
                    y = (int) screenHeight / 2 - (int) imageHeight / 2;
                }

                g.drawImage(image, x, y, this);
            }
        };
    }

    private void buildFrame() {
        setLayout(new BorderLayout());
        getContentPane().add(labelPanel, BorderLayout.SOUTH);
        getContentPane().add(panel, BorderLayout.CENTER);
        setIconImage(Toolkit.getDefaultToolkit().getImage("images/csun.gif"));
        setTitle("My App");
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }

    private void addMenuListeners() {

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                exitActionPerformed();
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aboutActionPerformed();
            }
        });

    }
    private void addTreeListeners() {
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int selRow = tree.getRowForLocation(mouseEvent.getX(), mouseEvent.getY());
                if(selRow != -1) {
                    treeClicked();
                }
            }
        });
    }

    private void treeClicked() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if(node != null && node.isLeaf()) {
            switch(node.toString()) {
                case "Area of Circle":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    AreaOfCircle areaOfCircle = AreaOfCircle.getInstance();
                    if(!areaOfCircle.isVisible()) {
                        areaOfCircle.setVisible(true);
                        desktop.add(areaOfCircle);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void exitActionPerformed() {
        dispose();
    }

    private void aboutActionPerformed() {
        JOptionPane.showMessageDialog(this, "Thx for using my app!");
    }

}
