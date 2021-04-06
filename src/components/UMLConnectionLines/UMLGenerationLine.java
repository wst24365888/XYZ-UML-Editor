package components.UMLConnectionLines;

import java.awt.*;

public class UMLGenerationLine extends BaseUMLConnectionLine {
    public UMLGenerationLine() {
        super();
    }

    public UMLGenerationLine(Point desPoint) {
        super(desPoint);
    }

    @Override
    public void drawArrow(Graphics graphics, Point source, Point destination) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        int sourceX = (int) source.getX();
        int sourceY = (int) source.getY();
        int destinationX = (int) destination.getX();
        int destinationY = (int) destination.getY();

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
