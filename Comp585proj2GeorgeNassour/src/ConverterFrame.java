/**
 * Created by George Nassour on 2/21/2018.
 * Building the frame and tree
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
    private DefaultMutableTreeNode tasksNode;

    //Math conversions
    private DefaultMutableTreeNode mathNode;
    private DefaultMutableTreeNode circleNode;
    private DefaultMutableTreeNode degreeToRadiansNode;
    private DefaultMutableTreeNode volumeOfSphereNode;

    //Metric conversions
    private DefaultMutableTreeNode metricNode;
    private DefaultMutableTreeNode poundToKilogramsNode;
    private DefaultMutableTreeNode mphToKPHNode;
    private DefaultMutableTreeNode gallonsToLitersNode;

    //Temperature Conversions
    private DefaultMutableTreeNode temperatureNode;
    private DefaultMutableTreeNode celsiusToFahrenheitNode;
    private DefaultMutableTreeNode celsiusToKelvinNode;
    private DefaultMutableTreeNode fahrenheitToCelsiusNode;

    //Speed Conversions
    private DefaultMutableTreeNode speedNode;
    private DefaultMutableTreeNode inchpsToFootPSNode;
    private DefaultMutableTreeNode inchpsToMeterPSNode;
    private DefaultMutableTreeNode inchpsToMilliPSNode;

    //Newtonian Force Conversions
    private DefaultMutableTreeNode forceNode;
    private DefaultMutableTreeNode kilogramMeterToPoundFeetNode;
    private DefaultMutableTreeNode newtonMeterToPoundFeetNode;
    private DefaultMutableTreeNode kilogramMeterToNewtonMeterNode;

    //Slow task node
    private DefaultMutableTreeNode slowTaskNode;

    //Contains the overall tree structure
    private DefaultTreeModel treeModel;

    public ConverterFrame(){initComponents();}

    //Initialize the basic components with values
    private void initComponents(){
        buildDesktop();
        initTree();
        buildTree();
        addTreeListeners();
        initMenu();
        addMenuListeners();
        initPanel();
        buildFrame();
    }

    //Initialize the tree nodes and add them to a tree
    private void initTree(){
        tasksNode = new DefaultMutableTreeNode("Conversions");
        slowTaskNode = new DefaultMutableTreeNode("Slow Task");
        //Math
        mathNode = new DefaultMutableTreeNode("Math");
        circleNode = new DefaultMutableTreeNode("Area of Circle");
        degreeToRadiansNode = new DefaultMutableTreeNode("Degree to Radians");
        volumeOfSphereNode = new DefaultMutableTreeNode("Volume of Sphere");
        //Metric
        metricNode = new DefaultMutableTreeNode("Metric");
        poundToKilogramsNode = new DefaultMutableTreeNode("Pound to Kilograms");
        mphToKPHNode = new DefaultMutableTreeNode("MPH to KPH");
        gallonsToLitersNode = new DefaultMutableTreeNode("Gallons to Liters");
        //Temperature
        temperatureNode = new DefaultMutableTreeNode("Temperature");
        celsiusToFahrenheitNode = new DefaultMutableTreeNode("Celsius to Fahrenheit");
        celsiusToKelvinNode = new DefaultMutableTreeNode("Celsius to Kelvin");
        fahrenheitToCelsiusNode = new DefaultMutableTreeNode("Fahrenheit to Celsius");
        //Speed
        speedNode = new DefaultMutableTreeNode("Speed");
        inchpsToFootPSNode = new DefaultMutableTreeNode("InchesPS to FeetPS");
        inchpsToMeterPSNode = new DefaultMutableTreeNode("InchesPS to MetersPS");
        inchpsToMilliPSNode = new DefaultMutableTreeNode("InchesPS to MilliPS");
        //Force
        forceNode = new DefaultMutableTreeNode("Force");
        kilogramMeterToPoundFeetNode = new DefaultMutableTreeNode("Kilogram/m to Pound/f");
        newtonMeterToPoundFeetNode = new DefaultMutableTreeNode("Newton/m to Pound/f");
        kilogramMeterToNewtonMeterNode = new DefaultMutableTreeNode("Kilogram/m to Newton/m");
    }

    private void buildTree(){
        //Math
        mathNode.add(circleNode);
        mathNode.add(degreeToRadiansNode);
        mathNode.add(volumeOfSphereNode);
        //metric
        metricNode.add(poundToKilogramsNode);
        metricNode.add(mphToKPHNode);
        metricNode.add(gallonsToLitersNode);
        //temperature
        temperatureNode.add(celsiusToFahrenheitNode);
        temperatureNode.add(celsiusToKelvinNode);
        temperatureNode.add(fahrenheitToCelsiusNode);
        //speed
        speedNode.add(inchpsToFootPSNode);
        speedNode.add(inchpsToMeterPSNode);
        speedNode.add(inchpsToMilliPSNode);
        //force
        forceNode.add(kilogramMeterToPoundFeetNode);
        forceNode.add(newtonMeterToPoundFeetNode);
        forceNode.add(kilogramMeterToNewtonMeterNode);

        tasksNode.add(mathNode);
        tasksNode.add(metricNode);
        tasksNode.add(temperatureNode);
        tasksNode.add(speedNode);
        tasksNode.add(forceNode);
        tasksNode.add(slowTaskNode);



        treeModel = new DefaultTreeModel(tasksNode);
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
        setSize(1100,750);
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
                    MathConversions mathConversions = MathConversions.getInstance();
                    if(!mathConversions.isVisible()) {
                        mathConversions.setVisible(true);
                        desktop.add(mathConversions);
                    }
                    break;
                case "Degree to Radians":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    DegreeToRadians degreeRadianConversion = DegreeToRadians.getInstance();
                    if(!degreeRadianConversion.isVisible()) {
                        degreeRadianConversion.setVisible(true);
                        desktop.add(degreeRadianConversion);
                    }
                    break;
                case "Volume of Sphere":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    VolumeOfSphere volumeOfSphere= VolumeOfSphere.getInstance();
                    if(!volumeOfSphere.isVisible()) {
                        volumeOfSphere.setVisible(true);
                        desktop.add(volumeOfSphere);
                    }
                    break;
                case "Pound to Kilograms":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    PoundToKilograms poundToKilograms = PoundToKilograms.getInstance();
                    if(!poundToKilograms.isVisible()) {
                        poundToKilograms.setVisible(true);
                        desktop.add(poundToKilograms);
                    }
                    break;
                case "MPH to KPH":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    MPHToKPH mphToKPH = MPHToKPH.getInstance();
                    if(!mphToKPH.isVisible()) {
                        mphToKPH.setVisible(true);
                        desktop.add(mphToKPH);
                    }
                    break;
                case "Gallons to Liters":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    GallonsToLiters gallonsToLiters = GallonsToLiters.getInstance();
                    if(!gallonsToLiters.isVisible()) {
                        gallonsToLiters.setVisible(true);
                        desktop.add(gallonsToLiters);
                    }
                    break;
                case "Celsius to Fahrenheit":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    CelsiusToFahrenheit celsiusToFahrenheit = CelsiusToFahrenheit.getInstance();
                    if(!celsiusToFahrenheit.isVisible()) {
                        celsiusToFahrenheit.setVisible(true);
                        desktop.add(celsiusToFahrenheit);
                    }
                    break;
                case "Celsius to Kelvin":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    CelsiusToKelvin celsiusToKelvin = CelsiusToKelvin.getInstance();
                    if(!celsiusToKelvin.isVisible()) {
                        celsiusToKelvin.setVisible(true);
                        desktop.add(celsiusToKelvin);
                    }
                    break;
                case "Fahrenheit to Celsius":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    FahrenheitToCelsius fahrenheitToCelsius = FahrenheitToCelsius.getInstance();
                    if(!fahrenheitToCelsius.isVisible()) {
                        fahrenheitToCelsius.setVisible(true);
                        desktop.add(fahrenheitToCelsius);
                    }
                    break;
                case "InchesPS to FeetPS":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    InchesPSToFeetPS inchesPSToFeetPS = InchesPSToFeetPS.getInstance();
                    if(!inchesPSToFeetPS.isVisible()) {
                        inchesPSToFeetPS.setVisible(true);
                        desktop.add(inchesPSToFeetPS);
                    }
                    break;
                case "InchesPS to MetersPS":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    InchesPSToMeterPS inchesPSToMeterPS = InchesPSToMeterPS.getInstance();
                    if(!inchesPSToMeterPS.isVisible()) {
                        inchesPSToMeterPS.setVisible(true);
                        desktop.add(inchesPSToMeterPS);
                    }
                    break;
                case "InchesPS to MilliPS":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    InchesPSToMilliPS inchesPSToMilliPS = InchesPSToMilliPS.getInstance();
                    if(!inchesPSToMilliPS.isVisible()) {
                        inchesPSToMilliPS.setVisible(true);
                        desktop.add(inchesPSToMilliPS);
                    }
                    break;
                case "Kilogram/m to Pound/f":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    KilogramMToPoundF kilogramMtoPoundF = KilogramMToPoundF.getInstance();
                    if(!kilogramMtoPoundF.isVisible()) {
                        kilogramMtoPoundF.setVisible(true);
                        desktop.add(kilogramMtoPoundF);
                    }
                    break;
                case "Newton/m to Pound/f":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    NewtonMToPoundF newtonMToPoundF = NewtonMToPoundF.getInstance();
                    if(!newtonMToPoundF.isVisible()) {
                        newtonMToPoundF.setVisible(true);
                        desktop.add(newtonMToPoundF);
                    }
                    break;
                case "Kilogram/m to Newton/m":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    KilogramMToNewtonM kilogramMToNewtonM = KilogramMToNewtonM.getInstance();
                    if(!kilogramMToNewtonM.isVisible()) {
                        kilogramMToNewtonM.setVisible(true);
                        desktop.add(kilogramMToNewtonM);
                    }
                    break;
                case "Slow Task":
                    // bring up the dialog box
                    statusLabel.setText(node.toString() + " clicked!");
                    LongTask longTask = LongTask.getInstance(this);
                    if(!longTask.isVisible()) {
                        longTask.setVisible(true);
                        desktop.add(longTask);
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
