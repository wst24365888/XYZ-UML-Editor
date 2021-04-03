package components.UMLObjects;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class BaseUMLObject extends JComponent {
    private static final long serialVersionUID = 2259886535991683392L;

    protected static int itemCounter = 0;
    protected int zAxisDepth = 0;

    protected int originalX;
    protected int originalY;

    protected boolean isPortVisible = false;

    protected ArrayList<Point> ports = new ArrayList<Point>();

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
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(this.isPortVisible) {
            for (Point point : ports) {
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillOval((int) point.getX(), (int) point.getY(), 10, 10);
            }
        }
    }

    protected void setPortVisible(boolean isVisible) {
        this.isPortVisible = isVisible;
        repaint();
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

    protected void onReleased() {
    }

    public int getZAxisDepth() {
        return this.zAxisDepth;
    }

    public void setDraggable(boolean isDraggable) {
        if (isDraggable) {
            this.addMouseListener(this.mouseListener);
            this.addMouseMotionListener(this.mouseListener);
        } else {
            this.removeMouseListener(this.mouseListener);
            this.removeMouseMotionListener(this.mouseListener);
        }
    }
}
