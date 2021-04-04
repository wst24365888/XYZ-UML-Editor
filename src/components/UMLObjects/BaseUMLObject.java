package components.UMLObjects;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComponent;

import components.UMLConnectionLines.BaseUMLConnectionLine;
import components.port.Port;
import widgets.Canvas;

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
    protected ArrayList<Map<BaseUMLObject, BaseUMLConnectionLine>> connections = new ArrayList<Map<BaseUMLObject, BaseUMLConnectionLine>>();

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

        Canvas.getInstance().repaint();
    }

    protected void onReleased() {
        
    };

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
        Canvas.getInstance().repaint();
    }

    public BaseUMLConnectionLine getDrawing() {
        return this.drawing;
    }

    public Point getPort(Point destination) {
        Point result;

        double angle = (double) Math.toDegrees(Math.atan2(destination.getX() - (this.getLocation().getX() + this.width / 2), destination.getY() - (this.getLocation().getY() + this.height / 2))) - 90;
    
        if (angle < 0) {
            angle += 360;
        }

        // System.out.println(angle);

        if (135 > angle && angle >= 45) {
            result = this.ports.getNorthPort();
        } else if (45 > angle || angle >= 270) {
            result = this.ports.getEastPort();
        } else if (315 > angle && angle >= 225) {
            result = this.ports.getSouthPort();
        } else {
            result = this.ports.getWestPort();
        }

        return new Point((int) (this.getLocation().getX() + result.getX()), (int) (this.getLocation().getY() + result.getY()));
    }

    public void addConntection(Map<BaseUMLObject, BaseUMLConnectionLine> connection) {
        this.connections.add(connection);
        Canvas.getInstance().repaint();
    }

    public ArrayList<Map<BaseUMLObject, BaseUMLConnectionLine>> getConntections() {
        return this.connections;
    }
}
