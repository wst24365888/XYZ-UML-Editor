package components.UMLObjects;

import java.awt.*;
import java.awt.event.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class BaseUMLObject {
    protected static int itemCounter = 0;
    protected int zAxisDepth = 0;

    protected ImageIcon imageIcon;
    protected JLabel label = new JLabel();

    protected int originalX;
    protected int originalY;

    public BaseUMLObject() {
        zAxisDepth = itemCounter++;

        MouseAdapter mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                onPressed(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                onDragged(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                onReleased();
            }
        };

        label.addMouseListener(mouseListener);
        label.addMouseMotionListener(mouseListener);
    }

    protected void onPressed(int x, int y) {
        System.out.printf("(%d, %d) - ", x, y);

        this.originalX = x;
        this.originalY = y;
    }

    protected void onDragged(int x, int y) {
        Point labelLocation = this.label.getLocation();

        System.out.printf("(%f, %f)\n", labelLocation.getX(), labelLocation.getY());

        this.label.setLocation(x + (int) labelLocation.getX() - originalX, y + (int) labelLocation.getY() - originalY);
    }

    protected void onReleased() {}

    protected void setImage(Icon icon) {
        this.label.setIcon(icon);
    }

    public JLabel getLabel() {
        return this.label;
    }

    public int getZAxisDepth() {
        return this.zAxisDepth;
    }
}
