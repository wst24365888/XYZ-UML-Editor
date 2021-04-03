package components.UMLObjects;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class BaseUMLObject extends JLabel {
	private static final long serialVersionUID = 2259886535991683392L;

	protected static int itemCounter = 0;
    protected int zAxisDepth = 0;

    protected ImageIcon imageIcon;

    protected int originalX;
    protected int originalY;

    private MouseAdapter mouseListener = new MouseAdapter() {
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

    public BaseUMLObject() {
        zAxisDepth = itemCounter++;

        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.setOpaque(true);
        this.setBackground(Color.LIGHT_GRAY);
    }

    protected void onPressed(int x, int y) {
        // System.out.printf("(%d, %d)\n", x, y);

        this.originalX = x;
        this.originalY = y;
    }

    protected void onDragged(int x, int y) {
        Point labelLocation = this.getLocation();

        // System.out.printf("(%f, %f)\n", labelLocation.getX(), labelLocation.getY());

        this.setLocation(x + (int) labelLocation.getX() - originalX, y + (int) labelLocation.getY() - originalY);
    }

    protected void onReleased() {}

    protected void setImage(ImageIcon icon) {
        this.setIcon(icon);
    }

    public int getZAxisDepth() {
        return this.zAxisDepth;
    }

    public void setDraggable(boolean isDraggable) {
        if(isDraggable) {
            this.addMouseListener(this.mouseListener);
            this.addMouseMotionListener(this.mouseListener);
        } else {
            this.removeMouseListener(this.mouseListener);
            this.removeMouseMotionListener(this.mouseListener);
        }
    }
}
