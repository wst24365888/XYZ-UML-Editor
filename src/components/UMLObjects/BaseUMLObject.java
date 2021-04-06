package components.UMLObjects;

import java.awt.*;

import javax.swing.JComponent;

import components.port.Port;
import widgets.Canvas;

public abstract class BaseUMLObject extends JComponent {
    private static final long serialVersionUID = 2259886535991683392L;

    protected static int itemCounter = 0;
    protected int zAxisHeight = 0;

    protected int width;
    protected int height;
    protected int offset;

    protected boolean isPortVisible = false;

    protected Port ports;

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

    public void setPortVisible(boolean isVisible) {
        this.isPortVisible = isVisible;
        this.repaint();
    }

    public int getZAxisHeight() {
        return this.zAxisHeight;
    }

    public Point getPort(Point destination) {
        Point result;

        double angle = (double) Math.toDegrees(Math.atan2(destination.getX() - this.getCenterLocation().getX(), destination.getY() - this.getCenterLocation().getY())) - 90;
    
        if (angle < 0) {
            angle += 360;
        }

        // System.out.println(angle);

        if (135 > angle && angle >= 45) {
            result = this.ports.getNorthPort();
        } else if (45 > angle || angle >= 315) {
            result = this.ports.getEastPort();
        } else if (315 > angle && angle >= 225) {
            result = this.ports.getSouthPort();
        } else {
            result = this.ports.getWestPort();
        }

        return new Point((int) (Canvas.getRelativeLocation(this.getLocationOnScreen()).getX() + result.getX()), (int) (Canvas.getRelativeLocation(this.getLocationOnScreen()).getY() + result.getY()));
    }

    public Point getCenterLocation() {
        return new Point((int) (Canvas.getRelativeLocation(this.getLocationOnScreen()).getX() + this.width / 2), (int) (Canvas.getRelativeLocation(this.getLocationOnScreen()).getY() + this.height / 2));
    }
}
