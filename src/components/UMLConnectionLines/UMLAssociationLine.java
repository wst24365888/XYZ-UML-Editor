package components.UMLConnectionLines;

import java.awt.*;

import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class UMLAssociationLine extends BaseUMLConnectionLine {
    public UMLAssociationLine(BaseUMLObject source, BaseUMLObject destination) {
        super(source, destination);
    }

    public UMLAssociationLine(BaseUMLObject source, Point mousePoint) {
        super(source, mousePoint);
    }

    @Override
    public void drawArrow(Graphics graphics) {
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

        graphics2D.setColor(Color.GRAY);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawLine(destinationX, destinationY, (int) arrowLeftX, (int) arrowLeftY);
        graphics2D.drawLine(destinationX, destinationY, (int) arrowRightX, (int) arrowRightY);
    }
}
