package project1;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Created by Gaming on 2/5/2018.
 */
public class ClickListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent event){
        CalculatorFrame.buttonClicked(event);
    }
}
