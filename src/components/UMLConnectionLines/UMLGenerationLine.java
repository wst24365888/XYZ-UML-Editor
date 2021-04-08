package components.UMLConnectionLines;

import java.awt.*;

import components.UMLObjects.BaseUMLObject;

public class UMLGenerationLine extends BaseUMLConnectionLine {
    public UMLGenerationLine(BaseUMLObject source, Point originalPoint, Point currentPoint) {
        super(source, originalPoint, currentPoint);
    }
    
    public UMLGenerationLine(BaseUMLObject source, Point originalPoint, BaseUMLObject destination, Point currentPoint) {
        super(source, originalPoint, destination, currentPoint);
    }

    @Override
    public void drawArrow(Graphics graphics) {
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

        int dx = destinationX - sourceX;
        int dy = destinationY - sourceY;

        double D = Math.sqrt(dx * dx + dy * dy);

        double arrowLeftX = D - 16;
        double arrowRightX = arrowLeftX;
        double arrowLeftY = 8;
        double arrowRightY = -8;
        double x;

        double sin = dy / D;
        double cos = dx / D;

        x = arrowLeftX * cos - arrowLeftY * sin + sourceX;
        arrowLeftY = arrowLeftX * sin + arrowLeftY * cos + sourceY;
        arrowLeftX = x;

        x = arrowRightX * cos - arrowRightY * sin + sourceX;
        arrowRightY = arrowRightX * sin + arrowRightY * cos + sourceY;
        arrowRightX = x;

        int[] xpoints = { destinationX, (int) arrowLeftX, (int) arrowRightX };
        int[] ypoints = { destinationY, (int) arrowLeftY, (int) arrowRightY };

        graphics2D.setStroke(new BasicStroke(6));

        graphics2D.setColor(Color.GRAY);
        graphics2D.drawPolygon(xpoints, ypoints, 3);

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillPolygon(xpoints, ypoints, 3);
    }
}
