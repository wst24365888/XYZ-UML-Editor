package components.UMLConnectionLines;

import java.awt.*;

import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public abstract class BaseUMLConnectionLine {
    protected BaseUMLObject source = null;
    protected BaseUMLObject destination = null;

    protected Point mousePoint = null;
    
    public BaseUMLConnectionLine(BaseUMLObject source, BaseUMLObject destination) {
        this.source = source;
        this.destination = destination;
    }
    
    public BaseUMLConnectionLine(BaseUMLObject source, Point mousePoint) {
        this.source = source;
        this.mousePoint = mousePoint;
    }

    public BaseUMLObject getSource() {
        return this.source;
    }

    public BaseUMLObject getDestination() {
        return this.destination;
    }

    public boolean alreadyHasConnection(BaseUMLObject source, BaseUMLObject destination) {
        if (this.source == source && this.destination == destination) {
            return true;
        }
        
        return false;
    }

    public void drawArrowLine(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        int sourceX;
        int sourceY;
        int destinationX;
        int destinationY;

        if (destination != null) {
            sourceX = (int) source.getPort(Canvas.getRelativeLocation(destination.getLocationOnScreen())).getX();
            sourceY = (int) source.getPort(Canvas.getRelativeLocation(destination.getLocationOnScreen())).getY();

            destinationX = (int) destination.getPort(Canvas.getRelativeLocation(source.getLocationOnScreen())).getX();
            destinationY = (int) destination.getPort(Canvas.getRelativeLocation(source.getLocationOnScreen())).getY();
        } else {
            sourceX = (int) source.getPort(mousePoint).getX();
            sourceY = (int) source.getPort(mousePoint).getY();

            destinationX = (int) mousePoint.getX();
            destinationY = (int) mousePoint.getY();
        }

        graphics2D.setColor(Color.GRAY);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawLine(sourceX, sourceY, destinationX, destinationY);
    }

    public abstract void drawArrow(Graphics graphics);
}
