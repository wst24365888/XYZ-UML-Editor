package components.UMLConnectionLines;

import java.awt.*;

public abstract class BaseUMLConnectionLine {
    private Point mousePoint;

    public BaseUMLConnectionLine() {

    }

    public BaseUMLConnectionLine(Point mousePoint) {
        this.mousePoint = mousePoint;
    }

    public Point getMousePoint() {
        return this.mousePoint;
    }

    public void drawArrowLine(Graphics graphics, Point source, Point destination) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        int sourceX = (int) source.getX();
        int sourceY = (int) source.getY();
        int destinationX = (int) destination.getX();
        int destinationY = (int) destination.getY();

        graphics2D.setColor(Color.GRAY);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawLine(sourceX, sourceY, destinationX, destinationY);
    }

    public abstract void drawArrow(Graphics graphics, Point source, Point destination);
}
