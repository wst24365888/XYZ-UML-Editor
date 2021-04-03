package widgets;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import mode.*;

public class ModeButton {
    protected JButton button = new JButton();
    protected ICanvasBehavior canvasBehavior;
        
    public ModeButton(ICanvasBehavior canvasBehavior) {        
        this.canvasBehavior = canvasBehavior;

        this.button.setBackground(Color.WHITE);
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClick();
            }
        });
    }
    
    protected void onClick() {
        Canvas.getInstance().setCanvasBehavior(this.canvasBehavior);
    }

    public JButton getButton() {
        return this.button;
    }
}
