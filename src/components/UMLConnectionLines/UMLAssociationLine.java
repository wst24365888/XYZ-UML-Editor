package components.UMLConnectionLines;

import java.awt.*;

public class UMLAssociationLine extends BaseUMLConnectionLine {
    public UMLAssociationLine() {
        super();
    }

    public UMLAssociationLine(Point desPoint) {
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
        double arrowLeftY = 12;
        double arrowRightY = -12;
        double x;

        double sin = dy / D;
        double cos = dx / D;

        x = arrowLeftX * cos - arrowLeftY * sin + sourceX;
        arrowLeftY = arrowLeftX * sin + arrowLeftY * cos + sourceY;
        arrowLeftX = x;

        x = arrowRightX * cos - arrowRightY * sin + sourceX;
        arrowRightY = arrowRightX * sin + arrowRightY * cos + sourceY;
        arrowRightX = x;

        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawLine(destinationX, destinationY, (int) arrowLeftX, (int) arrowLeftY);
        graphics2D.drawLine(destinationX, destinationY, (int) arrowRightX, (int) arrowRightY);
    }
}
