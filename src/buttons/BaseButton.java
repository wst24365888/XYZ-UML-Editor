package buttons;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import widgets.*;
import widgets.Canvas;
import canvas_behavior.*;

public abstract class BaseButton {
    protected static int itemCounter = 0;
    protected int index = 0;

    protected JButton button = new JButton();
    protected ICanvasBehavior canvasBehavior;

    public BaseButton(ICanvasBehavior canvasBehavior) {
        this.index = itemCounter++;

        this.canvasBehavior = canvasBehavior;

        this.button.setFocusable(false);
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
        Canvas.setCanvasBehavior(this.canvasBehavior);
    }

    public JButton getButton() {
        return this.button;
    }

    protected void setImage(ImageIcon icon) {
        this.button.setIcon(icon);
    }
}
