import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class NewtonMToPoundF extends JInternalFrame {

    private static NewtonMToPoundF instance = null;

    private JTextField textField;
    private JButton button;
    private JLabel label1, label2;
    private JPanel upperPanel, lowerPanel;

    public static NewtonMToPoundF getInstance() {
        if(instance == null) {
            instance = new NewtonMToPoundF();
        }
        return instance;
    }

    private NewtonMToPoundF() {
        //args: title, resisability, closability, maximizablity and iconifiability
        super("Area Of Circle", false, true, false, false);
        textField = new JTextField(10);
        button = new JButton("Area");
        label1 = new JLabel("Answer: ");
        label2 = new JLabel();
        upperPanel = new JPanel();
        lowerPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout()); //prob. not needed
        lowerPanel.setLayout(new FlowLayout()); //prob. not needed
        upperPanel.add(textField);
        upperPanel.add(button);
        lowerPanel.add(label1);
        lowerPanel.add(label2);
        add(upperPanel, BorderLayout.NORTH);
        add(lowerPanel, BorderLayout.SOUTH);

        addButtonListener();

        setBounds(25, 25, 250, 120);
        setLocation(100,100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void addButtonListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                areaOfCircle();
            }
        });
    }

    private void areaOfCircle() {
        label2.setText("");
        try {
            double radius = Double.parseDouble(textField.getText());
            double answer = Math.PI * (radius*radius);
            label2.setText(String.valueOf(answer));
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,"Hey buddy, enter a number!");
        }
    }


}
