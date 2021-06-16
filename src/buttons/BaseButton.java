package buttons;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import widgets.*;
import widgets.Canvas;

public abstract class BaseButton {
    protected static int itemCounter = 0;
    protected int index = 0;

    protected JButton button = new JButton();
    protected MouseAdapter canvasBehavior;

    public BaseButton(MouseAdapter canvasBehavior) {
        this.index = itemCounter++;

        this.canvasBehavior = canvasBehavior;

        this.button.setFocusable(false);
        this.button.setBackground(Color.WHITE);
        this.button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                onClick();
            }
        });

        this.setImage();
    }

    protected void onClick() {
        FunctionButtonPanel.resetPanel(this.index);
        Canvas.getInstance().setCanvasBehavior(this.canvasBehavior);
    }

    public JButton getButton() {
        return this.button;
    }

    protected abstract void setImage();
}
