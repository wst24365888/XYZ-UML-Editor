package components.UMLConnectionLines;

import java.awt.*;

import components.UMLObjects.BaseUMLObject;

public abstract class BaseUMLConnectionLine {
    protected BaseUMLObject source = null;
    protected BaseUMLObject destination = null;

    protected String sourcePortAlias = null;
    protected String destinationPortAlias = null;

    protected Point currentPoint = null;

    public BaseUMLConnectionLine(BaseUMLObject source, Point originalPoint, Point currentPoint) {
        this.currentPoint = currentPoint;
        this.source = source;
        this.sourcePortAlias = source.getPortAlias(originalPoint);
    }

    public BaseUMLConnectionLine(BaseUMLObject source, Point originalPoint, BaseUMLObject destination,
            Point currentPoint) {
        this.source = source;
        this.destination = destination;
        this.sourcePortAlias = source.getPortAlias(originalPoint);
        this.destinationPortAlias = destination.getPortAlias(currentPoint);
    }

    public void drawArrowLine(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        int sourceX;
        int sourceY;
        int destinationX;
        int destinationY;

        if (destination != null) {
            sourceX = (int) this.source.getPortByAlias(this.sourcePortAlias).getX();
            sourceY = (int) this.source.getPortByAlias(this.sourcePortAlias).getY();

            destinationX = (int) this.destination.getPortByAlias(this.destinationPortAlias).getX();
            destinationY = (int) this.destination.getPortByAlias(this.destinationPortAlias).getY();
        } else {
            sourceX = (int) this.source.getPortByAlias(this.sourcePortAlias).getX();
            sourceY = (int) this.source.getPortByAlias(this.sourcePortAlias).getY();

            destinationX = (int) currentPoint.getX();
            destinationY = (int) currentPoint.getY();
        }

        graphics2D.setColor(Color.GRAY);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawLine(sourceX, sourceY, destinationX, destinationY);
    }

    public abstract void drawArrow(Graphics graphics);
}
