package canvas_behavior;

import java.awt.*;

import components.UMLConnectionLines.BaseUMLConnectionLine;
import components.UMLConnectionLines.UMLCompositionLine;
import components.UMLObjects.BaseUMLObject;
import widgets.Canvas;

public class AddUMLCompositionLine implements ICanvasBehavior {
    private static AddUMLCompositionLine instance = null;

    private BaseUMLObject source = null;
    private BaseUMLObject destination = null;

    private Point originalPoint = null;

    private AddUMLCompositionLine() {
        // System.out.println("AddUMLCompositionLine created");
    };

    public static AddUMLCompositionLine getInstance() {
        if (instance == null) {
            instance = new AddUMLCompositionLine();
        }

        return instance;
    }

    @Override
    public void onPressed(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLCompositionLine onPressed");

        BaseUMLObject component = Canvas.getInstance().getPressedUMLClassesAndUseCases(mousePosX, mousePosY);
        if (component != null) {
            this.source = component;
            this.originalPoint = new Point(mousePosX, mousePosY);
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void onDragged(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLCompositionLine onDragged");

        if (this.source != null) {
            Canvas.getInstance().setDrawingLine(new UMLCompositionLine(this.source, this.originalPoint, new Point(mousePosX, mousePosY)));
        }

        Canvas.getInstance().repaint();
    }

    @Override
    public void onReleased(int mousePosX, int mousePosY) {
        // System.out.println("AddUMLCompositionLine onReleased");

        BaseUMLObject component = Canvas.getInstance().getPressedUMLClassesAndUseCases(mousePosX, mousePosY);
        if (component != null) {
            this.destination = component;
        }

        if (this.source != null && this.destination != null) {
            Canvas.getInstance().setDrawingLine(null);

            if (this.source != this.destination) {
                Canvas.getInstance().addUMLConntection(new UMLCompositionLine(this.source, this.originalPoint, this.destination, new Point(mousePosX, mousePosY)));
            }
        } else if (this.source != null && this.destination == null) {
            Canvas.getInstance().setDrawingLine(null);
        }

        this.source = null;
        this.destination = null;

        Canvas.getInstance().repaint();
    }
}
