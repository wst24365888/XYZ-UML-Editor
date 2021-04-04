package components.UMLObjects;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JComponent;

import components.UMLConnectionLines.BaseUMLConnectionLine;
import components.port.Port;

public abstract class BaseUMLObject extends JComponent {
    private static final long serialVersionUID = 2259886535991683392L;

    protected static int itemCounter = 0;
    protected int zAxisHeight = 0;

    protected int width;
    protected int height;
    protected int offset;

    protected int originalX;
    protected int originalY;

    protected boolean isPortVisible = false;

    protected Port ports;

    protected BaseUMLConnectionLine drawing;

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
        zAxisHeight = itemCounter++;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (this.isPortVisible) {
            for (Point point : this.ports.getPorts()) {
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.fillOval((int) point.getX() - this.offset, (int) point.getY() - this.offset, 10, 10);
            }
        }

        if (this.drawing != null) {
            System.out.println(drawing.getDesPoint().getX());
            System.out.println(drawing.getDesPoint().getY());

            drawing.drawArrowLine(graphics, this.getPort(drawing.getDesPoint()), drawing.getDesPoint());
        }
    }

    private Point getPort(Point destination) {
        double angle = (double) Math.toDegrees(Math.atan2(destination.getX() - (this.getLocation().getX() + this.width / 2), destination.getY() - (this.getLocation().getY() + this.height / 2))) - 90;
    
        if (angle < 0) {
            angle += 360;
        }

        // System.out.println(angle);

        if (135 > angle && angle >= 45) {
            return this.ports.getNorthPort();
        } else if (45 > angle || angle >= 270) {
            return this.ports.getEastPort();
        } else if (315 > angle && angle >= 225) {
            return this.ports.getSouthPort();
        } else {
            return this.ports.getWestPort();
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
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

    public int getZAxisHeight() {
        return this.zAxisHeight;
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

    public void setDrawingUMLConnectionLine(BaseUMLConnectionLine drawing) {
        this.drawing = drawing;
        repaint();
    }
}
