package canvas_behavior;

import java.awt.*;

import components.UMLConnectionLines.UMLAssociationLine;
import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class AddUMLAssociationLine implements ICanvasBehavior {
    private static AddUMLAssociationLine instance = null;

    private BaseUMLObject source = null;
    private BaseUMLObject destination = null;

    private Point originalPoint = null;

    private AddUMLAssociationLine() {
        // System.out.println("AddUMLAssociationLine created");
    };

    public static AddUMLAssociationLine getInstance() {
        if (instance == null) {
            instance = new AddUMLAssociationLine();
        }

        return instance;
    }

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLAssociationLine onPressed");

        BaseUMLObject component;

        component = Canvas.getInstance().getComponentWithin(mousePosX, mousePosY);

        if (component != null && component.isConnectable()) {
            this.source = component;
            this.originalPoint = new Point(mousePosX, mousePosY);
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLAssociationLine onDragged");

        if (this.source != null) {
            Canvas.getInstance().setDrawingLine(
                    new UMLAssociationLine(this.source, this.originalPoint, new Point(mousePosX, mousePosY)));
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLAssociationLine onReleased");

        BaseUMLObject component;

        component = Canvas.getInstance().getComponentWithin(mousePosX, mousePosY);

        if (component != null && component.isConnectable()) {
            this.destination = component;
        }

        if (this.source != null && this.destination != null) {
            Canvas.getInstance().setDrawingLine(null);

            if (this.source != this.destination) {
                Canvas.getInstance().addUMLConntection(new UMLAssociationLine(this.source, this.originalPoint,
                        this.destination, new Point(mousePosX, mousePosY)));
            }
        } else if (this.source != null && this.destination == null) {
            Canvas.getInstance().setDrawingLine(null);
        }

        this.source = null;
        this.destination = null;

        Canvas.getInstance().repaint();
    }
}
