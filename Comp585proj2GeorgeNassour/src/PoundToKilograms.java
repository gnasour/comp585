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

class PoundToKilograms extends JInternalFrame {

    private static PoundToKilograms instance = null;

    private JTextField textField;
    private JButton button;
    private JLabel label1, label2;
    private JPanel upperPanel, lowerPanel;

    public static PoundToKilograms getInstance() {
        if(instance == null) {
            instance = new PoundToKilograms();
        }
        return instance;
    }

    private PoundToKilograms() {
        //args: title, resisability, closability, maximizablity and iconifiability
        super("Pound to Kilogram", false, true, false, false);
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
                toKilograms();
            }
        });
    }

    private void toKilograms() {
        label2.setText("");
        try {
            double pounds = Double.parseDouble(textField.getText());
            double answer = pounds*.453592;
            label2.setText(String.valueOf(answer));
        }
        catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,"Enter a number!");
        }
    }


}
