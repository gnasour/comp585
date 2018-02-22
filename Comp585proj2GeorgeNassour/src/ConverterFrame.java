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
    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode math;
    private DefaultMutableTreeNode circle;
    private DefaultTreeModel treeModel;

    public ConverterFrame(){initComponenets();}

    //Initialize the basic components with values
    private void initComponenets(){
        initPanel();
        initTree();
    }

    //Initialize the tree nodes and add them to a tree
    private void initTree(){
        root = new DefaultMutableTreeNode("root");
        math = new DefaultMutableTreeNode("math");
        circle = new DefaultMutableTreeNode("circle");
        math.add(circle);
        root.add(math);
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
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
}
