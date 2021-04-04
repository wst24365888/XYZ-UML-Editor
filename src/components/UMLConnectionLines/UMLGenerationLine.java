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
    protected void drawArrow(Graphics graphics, int sourceX, int sourceY, int destinationX, int destinationY) {
        int dx = destinationX - sourceX;
        int dy = destinationY - sourceY;

        double D = Math.sqrt(dx * dx + dy * dy);

        double arrowLeftX = D - 24;
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

        graphics.fillPolygon(xpoints, ypoints, 3);
    }
}
