package buttons;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import widgets.Canvas;
import mode.*;

public abstract class BaseButton {
    protected JButton button = new JButton();
    protected ICanvasBehavior canvasBehavior;
        
    public BaseButton() {
        this.button.setBackground(Color.WHITE);
        this.button.addActionListener(new ActionListener() {
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
