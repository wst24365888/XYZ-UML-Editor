package components.UMLConnectionLines;

import java.awt.*;

import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class UMLCompositionLine extends BaseUMLConnectionLine {
    public UMLCompositionLine(BaseUMLObject source, BaseUMLObject destination) {
        super(source, destination);
    }

    public UMLCompositionLine(BaseUMLObject source, Point mousePoint) {
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

        double arrowLeftX = D - 10;
        double arrowRightX = arrowLeftX;
        double arrowLeftY = 10;
        double arrowRightY = -10;
        double x;

        double sin = dy / D;
        double cos = dx / D;

        x = arrowLeftX * cos - arrowLeftY * sin + sourceX;
        arrowLeftY = arrowLeftX * sin + arrowLeftY * cos + sourceY;
        arrowLeftX = x;

        x = arrowRightX * cos - arrowRightY * sin + sourceX;
        arrowRightY = arrowRightX * sin + arrowRightY * cos + sourceY;
        arrowRightX = x;

        double arrowBackX = arrowLeftX + (arrowRightX - destinationX);
        double arrowBackY = arrowLeftY + (arrowRightY - destinationY);

        int[] xpoints = { destinationX, (int) arrowLeftX, (int) arrowBackX, (int) arrowRightX };
        int[] ypoints = { destinationY, (int) arrowLeftY, (int) arrowBackY, (int) arrowRightY };

        graphics2D.setStroke(new BasicStroke(6));

        graphics2D.setColor(Color.GRAY);
        graphics2D.drawPolygon(xpoints, ypoints, 4);

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillPolygon(xpoints, ypoints, 4);
    }
}
