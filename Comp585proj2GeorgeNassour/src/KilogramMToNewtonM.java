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

class KilogramMToNewtonM extends JInternalFrame {

    private static KilogramMToNewtonM instance = null;

    private JTextField textField;
    private JButton button;
    private JLabel label1, label2;
    private JPanel upperPanel, lowerPanel;

    public static KilogramMToNewtonM getInstance() {
        if(instance == null) {
            instance = new KilogramMToNewtonM();
        }
        return instance;
    }

    private KilogramMToNewtonM() {
        //args: title, resisability, closability, maximizablity and iconifiability
        super("Kilogram/m to Newton/m", false, true, false, false);
        textField = new JTextField(10);
        button = new JButton("Calculate");
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
                toNm();
            }
        });
    }

    private void toNm() {
        label2.setText("");
        try {
            double kilogramM = Double.parseDouble(textField.getText());
            double answer = kilogramM/0.10197162129779;
            label2.setText(String.valueOf(answer) + " Nm");
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,"Enter a valid number!");
        }
    }


}
