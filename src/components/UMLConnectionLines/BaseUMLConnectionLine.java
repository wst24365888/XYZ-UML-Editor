package components.UMLConnectionLines;

import java.awt.*;

public abstract class BaseUMLConnectionLine {
    private Point desPoint;

    public BaseUMLConnectionLine(Point desPoint) {
        this.desPoint = desPoint;
    }

    public Point getDesPoint() {
        return this.desPoint;
    }

    public void drawArrowLine(Graphics graphics, Point source, Point destination) {
        int sourceX = (int) source.getX();
        int sourceY = (int) source.getY();
        int destinationX = (int) destination.getX();
        int destinationY = (int) destination.getY();

        graphics.drawLine(sourceX, sourceY, destinationX, destinationY);
        this.drawArrow(graphics, sourceX, sourceY, destinationX, destinationY);
    }

    protected abstract void drawArrow(Graphics graphics, int sourceX, int sourceY, int destinationX, int destinationY);
}
