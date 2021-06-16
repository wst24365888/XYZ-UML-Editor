package canvas_behavior;

import java.awt.*;
import java.awt.event.*;

import components.UMLConnectionLines.BaseUMLConnectionLine;
import components.UMLConnectionLines.UMLAssociationLine;
import components.UMLConnectionLines.UMLCompositionLine;
import components.UMLConnectionLines.UMLGeneralizationLine;
import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class AddUMLConnectionLine extends MouseAdapter {
    private String type;

    private BaseUMLObject source = null;
    private BaseUMLObject destination = null;

    private Point originalPoint = null;

    public AddUMLConnectionLine(String type) {
        this.type = type;
    }

    private BaseUMLConnectionLine createCompositionLine(BaseUMLObject source, Point originalPoint, Point currentPoint) {
        switch (this.type) {
            case "Association":
                return new UMLAssociationLine(source, originalPoint, currentPoint);
            case "Composition":
                return new UMLCompositionLine(source, originalPoint, currentPoint);
            case "Generalization":
                return new UMLGeneralizationLine(source, originalPoint, currentPoint);
            default:
                return null;
        }
    }

    private BaseUMLConnectionLine createCompositionLine(BaseUMLObject source, Point originalPoint,
            BaseUMLObject destination, Point currentPoint) {
        switch (this.type) {
            case "Association":
                return new UMLAssociationLine(source, originalPoint, destination, currentPoint);
            case "Composition":
                return new UMLCompositionLine(source, originalPoint, destination, currentPoint);
            case "Generalization":
                return new UMLGeneralizationLine(source, originalPoint, destination, currentPoint);
            default:
                return null;
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        BaseUMLObject component;

        component = Canvas.getInstance().getComponentWithin(mouseEvent.getX(), mouseEvent.getY());

        if (component != null && component.isConnectable()) {
            this.source = component;
            this.originalPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (this.source != null) {
            Canvas.getInstance().setDrawingLine(this.createCompositionLine(this.source, this.originalPoint,
                    new Point(mouseEvent.getX(), mouseEvent.getY())));
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        BaseUMLObject component;

        component = Canvas.getInstance().getComponentWithin(mouseEvent.getX(), mouseEvent.getY());

        if (component != null && component.isConnectable()) {
            this.destination = component;
        }

        if (this.source != null && this.destination != null) {
            Canvas.getInstance().setDrawingLine(null);

            if (this.source != this.destination) {
                Canvas.getInstance().addUMLConntectionLine(this.createCompositionLine(this.source, this.originalPoint,
                        this.destination, new Point(mouseEvent.getX(), mouseEvent.getY())));
            }
        } else if (this.source != null && this.destination == null) {
            Canvas.getInstance().setDrawingLine(null);
        }

        this.source = null;
        this.destination = null;

        Canvas.getInstance().repaint();
    }
}
