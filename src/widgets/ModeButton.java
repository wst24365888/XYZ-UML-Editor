package widgets;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import canvas_behavior.*;

public class ModeButton {
    protected static int itemCounter = 0;
    protected int index = 0;

    protected JButton button = new JButton();
    protected ICanvasBehavior canvasBehavior;
        
    public ModeButton(ICanvasBehavior canvasBehavior) {     
        this.index = itemCounter++;
        
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
        FunctionButtonPanel.resetPanel(this.index);
        Canvas.getInstance().setCanvasBehavior(this.canvasBehavior);
    }

    public JButton getButton() {
        return this.button;
    }
}
